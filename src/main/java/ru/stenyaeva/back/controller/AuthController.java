package ru.stenyaeva.back.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.stenyaeva.back.config.security.UserAuthentication;
import ru.stenyaeva.back.domain.dto.AuthDto;
import ru.stenyaeva.back.domain.dto.RegistrationUserDto;
import ru.stenyaeva.back.domain.exception.ValidationException;
import ru.stenyaeva.back.domain.utils.VerificationCodeGenerator;
import ru.stenyaeva.back.model.user.User;
import ru.stenyaeva.back.model.user.UserVerification;
import ru.stenyaeva.back.service.EmailService;
import ru.stenyaeva.back.service.UserService;
import ru.stenyaeva.back.service.VerificationService;
import ru.stenyaeva.back.service.impl.UserDetailsServiceImpl;
import ru.stenyaeva.back.domain.utils.UserRegistrationValidator;

import java.util.List;
import java.util.Map;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(maxAge = 3600L, methods = {RequestMethod.POST})
public class AuthController {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final UserRegistrationValidator userRegistrationValidator;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final VerificationService verificationService;
    private final EmailService emailService;


    @GetMapping("/isAuthenticated")
    public Map<String, Boolean> isAuthenticated(){
        return Map.of("isAuthenticated",SecurityContextHolder.getContext().getAuthentication() instanceof UserAuthentication);
    }

    @GetMapping("/csrf-token")
    public CsrfToken csrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }

    @PostMapping("/logout")
    public void logout(HttpSession httpSession, HttpServletRequest httpRequest, HttpServletResponse response){
        try{
            httpSession.invalidate();
            Cookie[] cookies = httpRequest.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    cookie.setValue(null);
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }catch (Exception e){
            System.err.println("Ошибка при выходе");
        }
    }

    @PostMapping("/register")
    public void registerUser(@Valid @RequestBody RegistrationUserDto dto,
                                          BindingResult errors) {
        userRegistrationValidator.validate(dto, errors);
        if (errors.hasErrors()) {
            throw new ValidationException(errors.getFieldErrors());
        }
        String code = VerificationCodeGenerator.generateVerificationCode();
        UserVerification userVerification = new UserVerification();
        userVerification.setEmail(dto.getEmail());
        userVerification.setVerificationCode(code);
        verificationService.save(userVerification);
        emailService.sendVerificationEmail(dto.getEmail(), code);
    }

    @PostMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@Valid @RequestBody RegistrationUserDto dto, @RequestParam("code") String code,
                              HttpServletRequest request, BindingResult errors){
        Boolean isVerification = verificationService.verifyCode(dto.getEmail(), code);
        if(isVerification){
            userService.save(new User(dto, List.of()));
            createSession(dto.getEmail(), request,null);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Неверный код!");
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthDto dto, HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = authenticationManager.authenticate(new UserAuthentication(dto));
        createSession(authentication.getName(), request, authentication);
        return "success";
    }

    private void createSession(String username,
                               HttpServletRequest request,
                               Authentication authentication) {
        SecurityContextHolder.getContext()
                .setAuthentication(
                        authentication == null ? new UserAuthentication(userDetailsServiceImpl.loadUserByUsername(username)) : authentication
                );
        HttpSession session = request.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, List<String>> handleValidationException(ValidationException e){
        return Map.of("errors",e.getErrors());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, String> handleBadCredentialsException(BadCredentialsException e){
        return Map.of("error",e.getMessage());
    }

}

package ru.stenyaeva.back.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.stenyaeva.back.config.security.UserAuthentication;
import ru.stenyaeva.back.domain.dto.AuthDto;
import ru.stenyaeva.back.domain.dto.RegistrationUserDto;
import ru.stenyaeva.back.domain.exception.ValidationException;
import ru.stenyaeva.back.model.user.User;
import ru.stenyaeva.back.service.UserService;
import ru.stenyaeva.back.service.impl.UserDetailsServiceImpl;
import ru.stenyaeva.back.domain.utils.UserRegistrationValidator;

import java.util.List;
import java.util.Map;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final UserRegistrationValidator userRegistrationValidator;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/logout")
    public void logout(HttpSession httpSession, HttpServletRequest httpRequest, HttpServletResponse response){
        try{
            httpSession.invalidate();
            Cookie[] cookies = httpRequest.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    cookie.setValue(null);
                    cookie.setPath("/"); // Убедитесь, что путь совпадает с тем, где были заданы cookies
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }catch (Exception e){
            System.err.println("Ошибка при logout");
        }
    }

    @PostMapping("/register")
    public void registerUser(@Valid @RequestBody RegistrationUserDto dto,
                                          BindingResult errors, HttpServletRequest request) {
        userRegistrationValidator.validate(dto, errors);
        if (errors.hasErrors()) {
            throw new ValidationException(errors.getFieldErrors());
        }
        userService.save(new User(dto, List.of()));
        createSession(dto.getEmail(), request,null);
    }

    @PostMapping("/login")
    public void login(@RequestBody AuthDto dto, HttpServletRequest request){
        Authentication authentication = authenticationManager.authenticate(new UserAuthentication(dto));
        createSession(authentication.getName(), request, authentication);
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

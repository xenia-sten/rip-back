package ru.stenyaeva.back.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.stenyaeva.back.domain.dto.AuthDto;
import ru.stenyaeva.back.domain.exception.AuthException;

@Component
@RequiredArgsConstructor
public class UserAuthenticationManager implements AuthenticationManager {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserAuthentication userAuthentication = (UserAuthentication) authentication;
        AuthDto authData = userAuthentication.getAuthDto();
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(authData.getEmail());
            if(!passwordEncoder.matches(authData.getPassword(), userDetails.getPassword())){
                throw new BadCredentialsException("Неправильный пароль!");
            }
        }catch (UsernameNotFoundException e){
            throw new BadCredentialsException(e.getMessage());
        }
        return new UserAuthentication(userDetailsService.loadUserByUsername(authData.getEmail()));
    }
}

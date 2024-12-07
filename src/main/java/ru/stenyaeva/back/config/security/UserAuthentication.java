package ru.stenyaeva.back.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.stenyaeva.back.domain.dto.AuthDto;
import ru.stenyaeva.back.model.user.User;
import ru.stenyaeva.back.model.user.UserDetailsImpl;

import java.util.Collection;

public class UserAuthentication implements Authentication {
    private UserDetails userDetails;
    private User user;
    private AuthDto authDto;
    private boolean isAuthenticated;

    public AuthDto getAuthDto() {
        return authDto;
    }

    public UserAuthentication(UserDetails userDetails) {
        isAuthenticated = true;
        this.userDetails = userDetails;
        this.user = ((UserDetailsImpl)userDetails).getUser();
    }

    public UserAuthentication(AuthDto authDto) {
        this.authDto = authDto;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return userDetails.getUsername();
    }
}

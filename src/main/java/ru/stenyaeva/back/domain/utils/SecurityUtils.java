package ru.stenyaeva.back.domain.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.stenyaeva.back.model.user.User;

@Component
public class SecurityUtils {

    public User getUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

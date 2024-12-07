package ru.stenyaeva.back.domain.exception;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


public class AuthException extends AuthenticationException {


    public AuthException(String message){
        super(message);
    }

    public AuthException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

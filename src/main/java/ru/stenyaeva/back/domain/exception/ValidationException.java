package ru.stenyaeva.back.domain.exception;


import lombok.Getter;
import org.aspectj.bridge.Message;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationException extends RuntimeException{
    private List<String> errors;

    public ValidationException(String message){
        errors = List.of(message);
    }

    public ValidationException(List<FieldError> errors){
        this.errors = new ArrayList<>();
        for(FieldError error: errors){
            this.errors.add(error.getDefaultMessage());
        }
    }
}

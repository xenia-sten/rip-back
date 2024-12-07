package ru.stenyaeva.back.domain.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.stenyaeva.back.domain.dto.RegistrationUserDto;
import ru.stenyaeva.back.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserRegistrationValidator implements Validator {
    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return RegistrationUserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegistrationUserDto dto = (RegistrationUserDto) target;
        if(!dto.getPassword().equals(dto.getConfirmPassword())){
            errors.rejectValue("password","","Пароли не совпадают");
        }
        if(userRepository.findByEmail(dto.getEmail()).isPresent()){
            errors.rejectValue("email","","Почта уже занята");
        }
    }
}

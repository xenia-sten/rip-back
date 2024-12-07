package ru.stenyaeva.back.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class RegistrationUserDto {
    @Length(min = 3, max = 50, message = "")
    private String username;
    @Length(min = 8, max = 16)
    //@Pattern(regexp = "")
    private String password;
    @Length(min = 8, max = 16)
    private String confirmPassword;
    @Email(message = "")
    private String email;
}

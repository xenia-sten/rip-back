package ru.stenyaeva.back.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthDto {
    private String email;
    private String password;
}

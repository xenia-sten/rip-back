package ru.stenyaeva.back.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.stenyaeva.back.model.user.User;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;

    public UserDto(User user){
        this.email = user.getEmail();
        this.id = user.getId();
        this.username = user.getUsername();
    }
}

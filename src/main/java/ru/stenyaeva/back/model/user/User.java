package ru.stenyaeva.back.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.stenyaeva.back.domain.dto.RegistrationUserDto;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @GeneratedValue
    @Id
    private Long id;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String password;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;

    public User(RegistrationUserDto dto, Collection<Role> roles){
        email = dto.getEmail();
        password = dto.getPassword();
        username = dto.getUsername();
        this.roles = roles;
    }
}

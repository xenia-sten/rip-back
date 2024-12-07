package ru.stenyaeva.back.service;

import ru.stenyaeva.back.model.user.User;

public interface UserService {
    User save(User user);
    User getUserById(Long id);
    User getUserByEmail(String email);
}

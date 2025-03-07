package ru.stenyaeva.back.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.stenyaeva.back.model.user.User;
import ru.stenyaeva.back.model.user.UserDetailsImpl;
import ru.stenyaeva.back.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(name);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь с таким email не найден");
        }
        return new UserDetailsImpl(user.get());
    }
}

package ru.stenyaeva.back.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stenyaeva.back.model.user.Role;
import ru.stenyaeva.back.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Role getUserRole(){
        return roleRepository.findByName("ROLE_USER").get();
    }
}

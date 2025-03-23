package ru.stenyaeva.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stenyaeva.back.model.user.UserVerification;

public interface UserVerificationRepository extends JpaRepository<UserVerification, Long> {
    UserVerification findByEmail(String email);
}

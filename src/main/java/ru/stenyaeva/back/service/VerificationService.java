package ru.stenyaeva.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stenyaeva.back.model.note.Folder;
import ru.stenyaeva.back.model.user.UserVerification;
import ru.stenyaeva.back.repository.UserVerificationRepository;

@Service
public class VerificationService {
    @Autowired
    private UserVerificationRepository verificationRepository;

    public boolean verifyCode(String email, String code) {
        UserVerification userVerification = verificationRepository.findByEmail(email);
        if (userVerification != null && userVerification.getVerificationCode().equals(code)) {
            // Verification successful
            return true;
        }
        // Verification failed
        return false;
    }
    public UserVerification save(UserVerification userVerification) {
        return verificationRepository.save(userVerification);
    }
}

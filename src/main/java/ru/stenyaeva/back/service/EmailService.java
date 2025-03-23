package ru.stenyaeva.back.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String toEmail, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ksusha.stenyeva@mail.ru");
        message.setTo(toEmail);
        message.setSubject("Email Verification Code");
        message.setText("Ваш код для подтверждения почты: " + verificationCode);
        mailSender.send(message);
    }
}

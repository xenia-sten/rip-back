package ru.stenyaeva.back.domain.utils;
import java.security.SecureRandom;

public class VerificationCodeGenerator {
    private static final String CHARACTERS = "0123456789";
    private static final int CODE_LENGTH = 6;
    private static final SecureRandom random = new SecureRandom();

    public static String generateVerificationCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return code.toString();
    }
}

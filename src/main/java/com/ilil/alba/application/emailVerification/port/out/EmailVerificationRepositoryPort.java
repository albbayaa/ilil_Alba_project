package com.ilil.alba.application.emailVerification.port.out;

import com.ilil.alba.domain.emailVerification.entity.EmailVerification;

import java.util.Optional;

public interface EmailVerificationRepositoryPort {
    EmailVerification save(EmailVerification emailVerification);
    Optional<EmailVerification> findByToken(String token);
    Optional<EmailVerification> findByEmail(String email);
    void delete(EmailVerification emailVerification);
}

package com.ilil.alba.adapter.out.persistence;

import com.ilil.alba.application.emailVerification.port.out.EmailVerificationRepositoryPort;
import com.ilil.alba.domain.emailVerification.entity.EmailVerification;
import com.ilil.alba.adapter.out.persistence.emailVerification.EmailVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmailVerificationJpaAdapter implements EmailVerificationRepositoryPort {

    private final EmailVerificationRepository emailVerificationRepository;

    @Override
    public EmailVerification save(EmailVerification emailVerification) {
        return emailVerificationRepository.save(emailVerification);
    }

    @Override
    public Optional<EmailVerification> findByToken(String token) {
        return emailVerificationRepository.findByToken(token);
    }

    @Override
    public Optional<EmailVerification> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public void delete(EmailVerification emailVerification) {
        emailVerificationRepository.delete(emailVerification);
    }
}

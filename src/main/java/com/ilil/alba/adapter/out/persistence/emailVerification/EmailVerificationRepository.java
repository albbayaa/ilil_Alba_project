package com.ilil.alba.adapter.out.persistence.emailVerification;

import com.ilil.alba.domain.emailVerification.entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {
    Optional<EmailVerification> findByToken(String token);
}

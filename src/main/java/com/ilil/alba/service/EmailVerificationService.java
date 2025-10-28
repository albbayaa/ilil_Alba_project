package com.ilil.alba.service;

import com.ilil.alba.common.exception.EmailVerificationException;
import com.ilil.alba.domain.emailVerification.entity.EmailVerification;
import com.ilil.alba.domain.member.entity.Member;
import com.ilil.alba.domain.base.IsCertification;
import com.ilil.alba.repository.jobPosting.EmailVerificationRepository;
import com.ilil.alba.repository.jobPosting.MemberJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.ilil.alba.common.response.status.BaseExceptionResponseStatus.INVALID_TOKEN;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {
    private final EmailVerificationRepository emailVerificationRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final JavaMailSender mailSender;

    @Transactional
    public void sendVerificationEmail(String email) {
        String token = UUID.randomUUID().toString();
        EmailVerification emailVerification = EmailVerification.builder()
                .email(email)
                .token(token)
                .expiredAt(LocalDateTime.now().plusMinutes(30)) // 30분 유효
                .build();

        emailVerificationRepository.save(emailVerification);

        String verificationLink = "http://localhost:8080/auth/verify-email?token=" + token;
        sendEmail(email, verificationLink);
    }

    private void sendEmail(String to, String verificationLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("이메일 인증");
        message.setText("아래 링크를 클릭하여 이메일 인증을 완료하세요: " + verificationLink);
        mailSender.send(message);
    }

    @Transactional
    public void verifyEmail(String token) {
        EmailVerification verification = emailVerificationRepository.findByToken(token)
                .orElseThrow(() -> new EmailVerificationException(INVALID_TOKEN));

        if (verification.isExpired()) {
            throw new IllegalStateException("인증 시간이 만료되었습니다.");
        }

        Member member = memberJpaRepository.findByEmail(verification.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일로 가입된 사용자가 없습니다."));

        member.updateIsCertification(IsCertification.TRUE);
        memberJpaRepository.save(member);
    }
}


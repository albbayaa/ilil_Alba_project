package com.ilil.alba.application.emailVerification.port.in;

public interface EmailVerificationPort {
    void sendVerificationEmail(String email);
    void verifyEmail(String token);
}

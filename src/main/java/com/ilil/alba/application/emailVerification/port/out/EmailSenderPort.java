package com.ilil.alba.application.emailVerification.port.out;

public interface EmailSenderPort {
    void sendEmail(String to, String subject, String body);
}

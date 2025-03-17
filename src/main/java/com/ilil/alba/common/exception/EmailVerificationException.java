package com.ilil.alba.common.exception;

import com.ilil.alba.common.response.status.ResponseStatus;

public class EmailVerificationException extends RuntimeException {
  private final ResponseStatus exceptionStatus;

  public EmailVerificationException(ResponseStatus exceptionStatus) {
    super(exceptionStatus.getMessage());
    this.exceptionStatus = exceptionStatus;
  }

  public EmailVerificationException(ResponseStatus exceptionStatus, String message) {
    super(message);
    this.exceptionStatus = exceptionStatus;
  }
}

package com.ilil.alba.common.exception;

import com.ilil.alba.common.response.status.ResponseStatus;
import lombok.Getter;

@Getter
public class JobApplicationException extends RuntimeException {

  private final ResponseStatus exceptionStatus;

  public JobApplicationException(ResponseStatus exceptionStatus) {
    super(exceptionStatus.getMessage());
    this.exceptionStatus = exceptionStatus;
  }

  public JobApplicationException(ResponseStatus exceptionStatus, String message) {
    super(message);
    this.exceptionStatus = exceptionStatus;
  }
}
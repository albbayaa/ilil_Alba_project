package com.ilil.alba.common.exception;

import com.ilil.alba.common.response.status.ResponseStatus;
import lombok.Getter;

@Getter
public class JobPostingException extends RuntimeException {

    private final ResponseStatus exceptionStatus;

    public JobPostingException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }

    public JobPostingException(ResponseStatus exceptionStatus, String message) {
        super(message);
        this.exceptionStatus = exceptionStatus;
    }
}
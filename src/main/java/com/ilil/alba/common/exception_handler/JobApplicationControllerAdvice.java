package com.ilil.alba.common.exception_handler;

import com.ilil.alba.common.exception.JobApplicationException;
import com.ilil.alba.common.exception.MemberException;
import com.ilil.alba.common.response.BaseErrorResponse;
import jakarta.annotation.Priority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Priority(0)
@RestControllerAdvice
public class JobApplicationControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JobApplicationException.class)
    public BaseErrorResponse handle_jobApplicationException(JobApplicationException e) {
        log.error("[handle_JobApplicationException]", e);
        return new BaseErrorResponse(e.getExceptionStatus(), e.getMessage());
    }
}

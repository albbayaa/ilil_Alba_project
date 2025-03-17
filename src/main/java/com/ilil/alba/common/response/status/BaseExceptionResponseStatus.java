package com.ilil.alba.common.response.status;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum BaseExceptionResponseStatus implements ResponseStatus{

    /**
     * 1000: 요청 성공 (OK)
     */
    SUCCESS(1000,HttpStatus.OK.value(), "요청에 성공하였습니다."),
    FAILURE(2000, HttpStatus.BAD_REQUEST.value(), "요청에 실패하였습니다."),
    /**
     * 공고 관련 code : 3000 대
     */
    NOT_FOUND_POSTING(3002, HttpStatus.NOT_FOUND.value(), "공고를 찾을 수 없습니다."),
    /**
     * 멤버 관련 code : 4000 대
     */
    ALREADY_EXIST_MEMBER(4000, HttpStatus.OK.value(), "이미 존재하는 회원입니다."),
    NOT_FOUND_MEMBER(4002, HttpStatus.NOT_FOUND.value(), "회원을 찾을 수 없습니다."),

    /**
     * 이메일 인증 관련 code : 5000 대
     * */
    INVALID_TOKEN(5000, HttpStatus.BAD_REQUEST.value(), "유효하지 않은 토큰입니다.");


    private final int code;
    private final int status;
    private final String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

package com.example.demo.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {
    NOT_FOUND_REVIEW(HttpStatus.NOT_FOUND, "해당하는 리뷰를 찾을 수 없습니다."),

    ACCESS_DENIEND_AUTHOR(HttpStatus.FORBIDDEN, "해당 글을 작성한 유저가 아닙니다."), // 임의로 설정한 code 추후 변경 및 추가 예정

    NOT_FOUND_EXHIBITION(HttpStatus.NOT_FOUND, "해당 전시를 찾을 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String message;

    ExceptionCode(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
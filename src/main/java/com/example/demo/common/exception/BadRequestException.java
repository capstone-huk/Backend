package com.example.demo.common.exception;

public class BadRequestException extends RuntimeException {
    private final ExceptionCode exceptionCode;;

    public BadRequestException(final ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}
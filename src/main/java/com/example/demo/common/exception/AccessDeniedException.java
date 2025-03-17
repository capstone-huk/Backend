package com.example.demo.common.exception;

public class AccessDeniedException extends RuntimeException {
  private final ExceptionCode exceptionCode;;

  public AccessDeniedException(final ExceptionCode exceptionCode) {
    super(exceptionCode.getMessage());
    this.exceptionCode = exceptionCode;
  }

  public ExceptionCode getExceptionCode() {
    return exceptionCode;
  }
}

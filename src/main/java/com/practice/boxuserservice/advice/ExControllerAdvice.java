package com.practice.boxuserservice.advice;

import com.practice.boxuserservice.global.aop.server_checked_error.AddServerCheckedErrorHeader;
import com.practice.boxuserservice.global.exception.DefaultServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ExControllerAdvice.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/24
 */
@RestControllerAdvice
public class ExControllerAdvice {

  @ExceptionHandler(DefaultServiceException.class)
  @AddServerCheckedErrorHeader
  public ResponseEntity<ErrorResult> defaultServiceExceptionHandler(DefaultServiceException e) {
    return new ResponseEntity<>(new ErrorResult(e.getMessage(), e.getCode()), e.getStatus());
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @AddServerCheckedErrorHeader
  public ResponseEntity<ErrorResult> httpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException e) {
    return new ResponseEntity<>(new ErrorResult("정의되지 않은 메소드입니다!", 2),
        HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler(Exception.class)
  @AddServerCheckedErrorHeader
  public ResponseEntity<ErrorResult> unexpectedError(Exception e) {
    return new ResponseEntity<>(new ErrorResult("정의되지 않은 에러입니다!", 1),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

package com.practice.boxuserservice.advice;

import com.practice.boxuserservice.global.aop.server_checked_error.AddServerCheckedErrorHeader;
import com.practice.boxuserservice.global.env.EnvUtil;
import com.practice.boxuserservice.global.exception.DefaultServiceException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ExControllerAdvice.
 *
 * @author : middlefitting
 * @since : 2023/08/24
 */
@RestControllerAdvice
@AllArgsConstructor
public class ExControllerAdvice {

  private final EnvUtil envUtil;

  @ExceptionHandler(DefaultServiceException.class)
  @AddServerCheckedErrorHeader
  public ResponseEntity<ErrorResult> defaultServiceExceptionHandler(DefaultServiceException e) {
    return new ResponseEntity<>(new ErrorResult(e.getMsg(), e.getCode()), e.getStatus());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @AddServerCheckedErrorHeader
  public ResponseEntity<ErrorResult> MethodArgumentNotValidError(
      MethodArgumentNotValidException e) {
    String msg = envUtil.getStringEnv("global.error.invalid-argument-request.msg");
    int code = envUtil.getIntegerEnv("global.error.invalid-argument-request.code");
    HttpStatus status = envUtil.getHttpStatusEnv("global.error.invalid-argument-request.status");

    return new ResponseEntity<>(new ErrorResult(msg, code), status);
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
    return new ResponseEntity<>(new ErrorResult("내부 서버 오류, 예상하지 못한 에러입니다!", 1),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

//  HttpMessageNotReadableException
}

package com.practice.boxuserservice.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * ServiceIllegalArgumentException.
 *
 * @author : middlefitting
 * @description:
 * @since : 2023/08/24
 */
@Getter
public class DefaultServiceException extends RuntimeException implements ServiceException {

  private final int code;

  private final HttpStatus status;

  public DefaultServiceException(String msg, int code, HttpStatus status) {
    super(msg);
    this.code = code;
    this.status = status;
  }
}

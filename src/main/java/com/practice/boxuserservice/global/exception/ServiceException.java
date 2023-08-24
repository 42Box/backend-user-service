package com.practice.boxuserservice.global.exception;

import org.springframework.http.HttpStatus;

/**
 * ServiceException.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/24
 */
public interface ServiceException {

  HttpStatus getStatus();

  int getCode();

  String getMessage();
}

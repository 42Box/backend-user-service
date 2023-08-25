package com.practice.boxuserservice.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * ErrorResult.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/24
 */
@AllArgsConstructor
@Data
public class ErrorResult {

  private String msg;
  private int code;
}

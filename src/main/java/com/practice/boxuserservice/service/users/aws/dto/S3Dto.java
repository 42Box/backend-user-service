package com.practice.boxuserservice.service.users.aws.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * S3Dto.
 *
 * @author : middlefitting
 * @since : 2023/08/27
 */
@Setter
@Getter
@AllArgsConstructor
public class S3Dto {

  private String path;
  private String url;
}

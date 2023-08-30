package com.practice.boxuserservice.service.my_scripts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PostMyScriptsDto.
 *
 * @author : middlefitting
 * @since : 2023/08/31
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostMyScriptsDto {

  private String name;

  private String description;

  private String path;

  private String userUuid;
}

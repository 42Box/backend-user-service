package com.practice.boxuserservice.service.my_scripts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PutMyScriptsDto.
 *
 * @author : middlefitting
 * @since : 2023/08/31
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PutMyScriptsDto {

  private String name;

  private String description;

  private String path;

  private String userUuid;
}

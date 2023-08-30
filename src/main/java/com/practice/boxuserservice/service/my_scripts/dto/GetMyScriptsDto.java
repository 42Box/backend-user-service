package com.practice.boxuserservice.service.my_scripts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * GetMyScriptsDto.
 *
 * @author : middlefitting
 * @since : 2023/08/31
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetMyScriptsDto {

  private Long savedId;
  private String userUuid;
}

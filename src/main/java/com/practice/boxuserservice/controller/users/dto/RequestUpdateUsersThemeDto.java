package com.practice.boxuserservice.controller.users.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RequestUpdateUsersThemeDto.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/25
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestUpdateUsersThemeDto {

  @NotNull
  private Integer theme;
}

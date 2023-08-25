package com.practice.boxuserservice.controller.users.dto;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RequestUpdateUsersIconDto.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/25
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestUpdateUsersIconDto {

  @NotEmpty
  private String icon;
}

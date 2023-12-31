package com.practice.boxuserservice.controller.users.dto;

import com.practice.boxuserservice.entity.users.type.UsersIcon;
import com.practice.boxuserservice.service.users.dto.UpdateUsersIconDto;
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

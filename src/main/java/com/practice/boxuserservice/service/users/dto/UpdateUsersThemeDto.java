package com.practice.boxuserservice.service.users.dto;

import com.practice.boxuserservice.controller.users.dto.RequestUpdateUsersThemeDto;
import com.practice.boxuserservice.entity.users.type.UsersTheme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UpdateUsersThemeDto.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/25
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUsersThemeDto {

  private String uuid;
  private UsersTheme theme;

  public UpdateUsersThemeDto(RequestUpdateUsersThemeDto reqDto) {
    this.theme = UsersTheme.fromIndex(reqDto.getTheme());
  }
}

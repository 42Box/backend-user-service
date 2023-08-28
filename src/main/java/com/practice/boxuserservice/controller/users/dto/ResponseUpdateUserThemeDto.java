package com.practice.boxuserservice.controller.users.dto;

import com.practice.boxuserservice.service.users.dto.UpdateUsersThemeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ResponseUpdateUserIconDto.
 *
 * @author : middlefitting
 * @since : 2023/08/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUpdateUserThemeDto {

  private int theme;

  public ResponseUpdateUserThemeDto(UpdateUsersThemeDto dto) {
    this.theme = dto.getTheme().getIndex();
  }
}

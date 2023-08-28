package com.practice.boxuserservice.controller.users.dto;

import com.practice.boxuserservice.service.users.dto.UpdateUsersIconDto;
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
public class ResponseUpdateUserIconDto {

  private String icon;

  public ResponseUpdateUserIconDto(UpdateUsersIconDto dto) {
    this.icon = dto.getIcon().getValue();
  }
}

package com.practice.boxuserservice.service.users.dto;

import com.practice.boxuserservice.entity.users.type.UsersIcon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UpdateUsersIconDto.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/25
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUsersIconDto {

  private UsersIcon icon;
}

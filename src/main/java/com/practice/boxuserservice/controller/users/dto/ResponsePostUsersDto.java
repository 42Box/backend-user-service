package com.practice.boxuserservice.controller.users.dto;

import com.practice.boxuserservice.entity.users.type.UsersRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ResponsePostUsersDto.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePostUsersDto {

  private UsersRole role;
  private String uuid;
}

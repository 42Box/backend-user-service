package com.practice.boxuserservice.service.users.dto;

import com.practice.boxuserservice.entity.users.type.UsersRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PostUserDto.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUsersDto {

  private String nickname;
  private UsersRole role;
  private Integer cursusId;
  private Integer campusId;
}

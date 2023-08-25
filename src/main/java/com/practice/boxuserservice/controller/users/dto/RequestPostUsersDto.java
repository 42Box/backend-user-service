package com.practice.boxuserservice.controller.users.dto;

import com.practice.boxuserservice.entity.users.type.UsersRole;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * RequestPostUsersDto.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestPostUsersDto {

  private String nickname;
  @NotNull
  private UsersRole role;
  private Integer cursusId;
  private Integer campusId;
}

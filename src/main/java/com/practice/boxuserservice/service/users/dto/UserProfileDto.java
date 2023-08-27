package com.practice.boxuserservice.service.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserDto.
 *
 * @author : middlefitting
 * @since : 2023/08/25
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {

  private String uuid;
  private String nickname;
  private String profileImageUrl;
  private String profileImagePath;
  private String statusMessage;
}

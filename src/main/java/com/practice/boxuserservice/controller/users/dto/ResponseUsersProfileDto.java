package com.practice.boxuserservice.controller.users.dto;

import com.practice.boxuserservice.service.users.dto.UserProfileDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ResponseusersDto.
 *
 * @author : middlefitting
 * @since : 2023/08/24
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseUsersProfileDto {

  private String uuid;
  private String nickname;
  private String profileImageUrl;
  private String profileImagePath;
  private String statusMessage;
  private String bigProfileImagePath;


  public ResponseUsersProfileDto(UserProfileDto dto) {
    uuid = dto.getUuid();
    nickname = dto.getNickname();
    profileImageUrl = dto.getProfileImageUrl();
    profileImagePath = dto.getProfileImagePath();
    statusMessage = dto.getStatusMessage();
    bigProfileImagePath = dto.getBigProfileImagePath();
  }
}

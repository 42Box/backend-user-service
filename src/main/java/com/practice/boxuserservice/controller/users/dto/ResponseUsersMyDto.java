package com.practice.boxuserservice.controller.users.dto;

import com.practice.boxuserservice.entity.users.type.UsersUrl;
import com.practice.boxuserservice.service.users.dto.UserMyPageDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ResponseusersDto.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/24
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseUsersMyDto {

  private String uuid;
  private String nickname;
  private int theme;
  private String icon;
  private List<UsersUrl> urlList;
  private String profileImage;

  public ResponseUsersMyDto(UserMyPageDto dto) {
    uuid = dto.getUuid();
    nickname = dto.getNickname();
    theme = dto.getTheme().getIndex();
    icon = dto.getIcon().getValue();
    urlList = dto.getUrlList();
    profileImage = dto.getProfileImage();
  }
}

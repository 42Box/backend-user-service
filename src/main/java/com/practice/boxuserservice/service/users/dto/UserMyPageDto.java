package com.practice.boxuserservice.service.users.dto;

import com.practice.boxuserservice.entity.users.type.UsersIcon;
import com.practice.boxuserservice.entity.users.type.UsersTheme;
import com.practice.boxuserservice.entity.users.type.UsersUrl;
import java.util.List;
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
public class UserMyPageDto {

  private String uuid;
  private String nickname;
  private UsersTheme theme;
  private UsersIcon icon;
  private List<UsersUrl> urlList;
  private String profileImageUrl;
  private String profileImagePath;
  private String statusMessage;
}

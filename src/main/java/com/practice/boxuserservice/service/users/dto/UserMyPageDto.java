package com.practice.boxuserservice.service.users.dto;

import com.practice.boxuserservice.entity.users.type.UsersIcon;
import com.practice.boxuserservice.entity.users.type.UsersTheme;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.util.function.Tuple2;

/**
 * UserDto.
 *
 * @author : middlefitting
 * @description :
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
  private List<Tuple2<String, String>> urlList;
  private String profileImage;
}

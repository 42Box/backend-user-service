package com.practice.boxuserservice.service.users.dto;

import com.practice.boxuserservice.entity.users.type.UsersRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * PostUsersResultDto.
 *
 * @author : middlefitting
 * @since : 2023/08/25
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostUsersResultDto {

  private UsersRole role;
  private String uuid;
  private String profileImageUrl;
  private String profileImagePath;
  private HttpStatus status;
}

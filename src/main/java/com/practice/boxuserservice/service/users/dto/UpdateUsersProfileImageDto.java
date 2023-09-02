package com.practice.boxuserservice.service.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * UpdateUsersProfileImageDto.
 *
 * @author : middlefitting
 * @since : 2023/08/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUsersProfileImageDto {

  private String profileImagePath;
  private MultipartFile profileImageFile;
  private String bigProfileImagePath;
}

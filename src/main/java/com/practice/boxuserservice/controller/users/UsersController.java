package com.practice.boxuserservice.controller.users;

import com.practice.boxuserservice.controller.users.dto.RequestPostUsersDto;
import com.practice.boxuserservice.controller.users.dto.RequestUpdateUsersIconDto;
import com.practice.boxuserservice.controller.users.dto.RequestUpdateUsersStatusMessage;
import com.practice.boxuserservice.controller.users.dto.RequestUpdateUsersThemeDto;
import com.practice.boxuserservice.controller.users.dto.RequestUpdateUsersUrlListDto;
import com.practice.boxuserservice.controller.users.dto.ResponsePostUsersDto;
import com.practice.boxuserservice.controller.users.dto.ResponseUsersMyDto;
import com.practice.boxuserservice.controller.users.dto.ResponseUsersProfileDto;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * UsersController.
 *
 * @author : middlefitting
 * @since : 2023/08/24
 */
public interface UsersController {

  ResponseEntity<ResponsePostUsersDto> saveUser(HttpServletRequest request,
      RequestPostUsersDto dto);

  ResponseEntity<ResponseUsersProfileDto> getProfile(String uuid, HttpServletRequest request);

  ResponseEntity<ResponseUsersMyDto> getMyPage(HttpServletRequest request);

  ResponseEntity<Void> updateUserTheme(HttpServletRequest request, RequestUpdateUsersThemeDto dto);

  ResponseEntity<Void> updateUserIcon(HttpServletRequest request, RequestUpdateUsersIconDto dto);

  ResponseEntity<Void> updateUserUrlList(HttpServletRequest request,
      RequestUpdateUsersUrlListDto dto);

  ResponseEntity<Void> updateUserStatusMessage(HttpServletRequest request,
      RequestUpdateUsersStatusMessage dto);

  ResponseEntity<Void> updateUserProfileImage(HttpServletRequest request, MultipartFile file);
}

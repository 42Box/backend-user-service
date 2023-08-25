package com.practice.boxuserservice.controller.users;

import com.practice.boxuserservice.controller.users.dto.RequestPostUsersDto;
import com.practice.boxuserservice.controller.users.dto.ResponseUsersMyDto;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

/**
 * UsersController.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/24
 */
public interface UsersController {

  ResponseEntity<Void> saveUser(HttpServletRequest request, RequestPostUsersDto dto);

  ResponseEntity<ResponseUsersMyDto> getMyPage(HttpServletRequest request);
}

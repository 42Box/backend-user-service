package com.practice.boxuserservice.controller.users;

import com.practice.boxuserservice.controller.users.dto.RequestPostUsersDto;
import com.practice.boxuserservice.controller.users.dto.RequestUpdateUsersIconDto;
import com.practice.boxuserservice.controller.users.dto.RequestUpdateUsersThemeDto;
import com.practice.boxuserservice.controller.users.dto.RequestUpdateUsersUrlListDto;
import com.practice.boxuserservice.controller.users.dto.ResponseUsersMyDto;
import com.practice.boxuserservice.service.users.UsersService;
import com.practice.boxuserservice.service.users.dto.PostUsersDto;
import com.practice.boxuserservice.service.users.dto.UserMyPageDto;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UsersController.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/24
 */
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersControllerImpl implements UsersController {

  private final UsersService usersService;

  private final ModelMapper modelMapper;

  @Override
  @PostMapping
  public ResponseEntity<Void> saveUser(HttpServletRequest request,
      @Valid @RequestBody RequestPostUsersDto requestDto) {
    PostUsersDto dto = modelMapper.map(requestDto, PostUsersDto.class);
    usersService.saveUser(dto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  @GetMapping("/me")
  public ResponseEntity<ResponseUsersMyDto> getMyPage(HttpServletRequest request) {
    String nickname = request.getHeader("nickname");
    UserMyPageDto dto = usersService.getUserByNickname(nickname);
//    ResponseUsersMyDto responseDto = modelMapper.map(dto, ResponseUsersMyDto.class);
    ResponseUsersMyDto responseDto = new ResponseUsersMyDto(dto);
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }

  @Override
  @PutMapping("/me/theme")
  public ResponseEntity<Void> updateUserTheme(HttpServletRequest request,
      RequestUpdateUsersThemeDto dto) {
    return null;
  }

  @Override
  @PutMapping("/me/icon")
  public ResponseEntity<Void> updateUserIcon(HttpServletRequest request,
      RequestUpdateUsersIconDto dto) {
    return null;
  }

  @Override
  @PutMapping("/me/url-list")
  public ResponseEntity<Void> updateUserUrlList(HttpServletRequest request,
      @Valid @RequestBody RequestUpdateUsersUrlListDto dto) {
//    UpdateUsersUrlListDto updateDto = modelMapper.map(dto, UpdateUsersUrlListDto.class);
//    System.out.println(dto.getUrlList().toString());
    return null;
  }
}

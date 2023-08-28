package com.practice.boxuserservice.controller.users;

import com.practice.boxuserservice.controller.users.dto.RequestPostUsersDto;
import com.practice.boxuserservice.controller.users.dto.RequestUpdateUsersIconDto;
import com.practice.boxuserservice.controller.users.dto.RequestUpdateUsersStatusMessage;
import com.practice.boxuserservice.controller.users.dto.RequestUpdateUsersThemeDto;
import com.practice.boxuserservice.controller.users.dto.RequestUpdateUsersUrlListDto;
import com.practice.boxuserservice.controller.users.dto.ResponsePostUsersDto;
import com.practice.boxuserservice.controller.users.dto.ResponseUpdateUserIconDto;
import com.practice.boxuserservice.controller.users.dto.ResponseUpdateUserProfileImageDto;
import com.practice.boxuserservice.controller.users.dto.ResponseUpdateUserStatusMessageDto;
import com.practice.boxuserservice.controller.users.dto.ResponseUpdateUserThemeDto;
import com.practice.boxuserservice.controller.users.dto.ResponseUpdateUserUrlListDto;
import com.practice.boxuserservice.controller.users.dto.ResponseUsersMyDto;
import com.practice.boxuserservice.controller.users.dto.ResponseUsersProfileDto;
import com.practice.boxuserservice.global.aop.validate_nickname_header.HeaderAuthCheck;
import com.practice.boxuserservice.global.env.EnvUtil;
import com.practice.boxuserservice.global.exception.DefaultServiceException;
import com.practice.boxuserservice.service.users.UsersService;
import com.practice.boxuserservice.service.users.dto.PostUsersDto;
import com.practice.boxuserservice.service.users.dto.PostUsersResultDto;
import com.practice.boxuserservice.service.users.dto.UpdateUsersIconDto;
import com.practice.boxuserservice.service.users.dto.UpdateUsersProfileImageDto;
import com.practice.boxuserservice.service.users.dto.UpdateUsersStatusMessage;
import com.practice.boxuserservice.service.users.dto.UpdateUsersThemeDto;
import com.practice.boxuserservice.service.users.dto.UpdateUsersUrlListDto;
import com.practice.boxuserservice.service.users.dto.UserMyPageDto;
import com.practice.boxuserservice.service.users.dto.UserProfileDto;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * UsersController.
 *
 * @author : middlefitting
 * @since : 2023/08/24
 */
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class Users42ControllerImpl implements UsersController {

  private final UsersService usersService;
  private final ModelMapper modelMapper;
  private final EnvUtil envUtil;

  @Override
  @PostMapping("/42")
  public ResponseEntity<ResponsePostUsersDto> saveUser(HttpServletRequest request,
      @Valid @RequestBody RequestPostUsersDto requestDto) {
    PostUsersDto dto = modelMapper.map(requestDto, PostUsersDto.class);
    PostUsersResultDto resultDto = usersService.saveUser(dto);
    ResponsePostUsersDto responseDto = modelMapper.map(resultDto, ResponsePostUsersDto.class);
    return ResponseEntity.status(resultDto.getStatus()).body(responseDto);
  }

  @Override
  @GetMapping("/profile/{uuid}")
  public ResponseEntity<ResponseUsersProfileDto> getProfile(@PathVariable @NotEmpty String uuid,
      HttpServletRequest request) {
    UserProfileDto dto = usersService.getUserProfileByUuid(uuid);

    ResponseUsersProfileDto responseDto = new ResponseUsersProfileDto(dto);
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }

  @Override
  @HeaderAuthCheck
  @GetMapping("/me")
  public ResponseEntity<ResponseUsersMyDto> getMyPage(HttpServletRequest request) {
    String uuid = request.getHeader("uuid");
    UserMyPageDto dto = usersService.getUserByUuid(uuid);
    ResponseUsersMyDto responseDto = new ResponseUsersMyDto(dto);
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }

  @Override
  @HeaderAuthCheck
  @PutMapping("/me/theme")
  public ResponseEntity<ResponseUpdateUserThemeDto> updateUserTheme(HttpServletRequest request,
      @RequestBody @Valid RequestUpdateUsersThemeDto dto) {
    String uuid = request.getHeader("uuid");
    UpdateUsersThemeDto updateDto;
    try {
      updateDto = new UpdateUsersThemeDto(dto);
    } catch (IllegalArgumentException e) {
      throw new DefaultServiceException("users.error.invalid-icon", envUtil);
    }
    updateDto.setUuid(uuid);
    usersService.updateUserTheme(updateDto);
    ResponseUpdateUserThemeDto responseDto = new ResponseUpdateUserThemeDto(updateDto);
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }

  @Override
  @HeaderAuthCheck
  @PutMapping("/me/icon")
  public ResponseEntity<ResponseUpdateUserIconDto> updateUserIcon(HttpServletRequest request,
      @RequestBody @Valid RequestUpdateUsersIconDto dto) {
    String uuid = request.getHeader("uuid");
    UpdateUsersIconDto updateDto;
    try {
      updateDto = new UpdateUsersIconDto(dto);
    } catch (IllegalArgumentException e) {
      throw new DefaultServiceException("users.error.invalid-icon", envUtil);
    }
    updateDto.setUuid(uuid);
    usersService.updateUserIcon(updateDto);
    ResponseUpdateUserIconDto responseDto = new ResponseUpdateUserIconDto(updateDto);
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }

  @Override
  @HeaderAuthCheck
  @PutMapping("/me/url-list")
  public ResponseEntity<ResponseUpdateUserUrlListDto> updateUserUrlList(HttpServletRequest request,
      @Valid @RequestBody RequestUpdateUsersUrlListDto dto) {
    String uuid = request.getHeader("uuid");
    UpdateUsersUrlListDto updateDto = modelMapper.map(dto, UpdateUsersUrlListDto.class);
    updateDto.setUuid(uuid);
    usersService.updateUserUrlList(updateDto);
    ResponseUpdateUserUrlListDto responseDto = modelMapper.map(updateDto,
        ResponseUpdateUserUrlListDto.class);
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }

  @Override
  @HeaderAuthCheck
  @PutMapping("/me/status-message")
  public ResponseEntity<ResponseUpdateUserStatusMessageDto> updateUserStatusMessage(
      HttpServletRequest request,
      @Valid @RequestBody RequestUpdateUsersStatusMessage dto) {
    String uuid = request.getHeader("uuid");
    UpdateUsersStatusMessage updateDto = modelMapper.map(dto, UpdateUsersStatusMessage.class);
    updateDto.setUuid(uuid);
    usersService.updateUserStatusMessage(updateDto);
    ResponseUpdateUserStatusMessageDto responseDto = modelMapper.map(updateDto,
        ResponseUpdateUserStatusMessageDto.class);
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }

  @Override
  @HeaderAuthCheck
  @PutMapping("/me/profile-image")
  public ResponseEntity<ResponseUpdateUserProfileImageDto> updateUserProfileImage(
      HttpServletRequest request,
      @RequestParam("profile-image") MultipartFile file) {
    String profileImagePath = request.getHeader("profileImagePath");
    String profileImageUrl = request.getHeader("profileImageUrl");
    if (!"image/png".equals(file.getContentType())) {
      throw new DefaultServiceException("users.error.invalid-image-format", envUtil);
    }
    UpdateUsersProfileImageDto dto = new UpdateUsersProfileImageDto(profileImagePath, file);
    usersService.updateUserProfileImage(dto);
    ResponseUpdateUserProfileImageDto responseDto = modelMapper.map(dto,
        ResponseUpdateUserProfileImageDto.class);
    responseDto.setProfileImageUrl(profileImageUrl);
    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }

}

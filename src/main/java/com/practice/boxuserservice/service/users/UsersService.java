package com.practice.boxuserservice.service.users;

import com.practice.boxuserservice.entity.users.UsersEntity;
import com.practice.boxuserservice.global.env.EnvUtil;
import com.practice.boxuserservice.global.exception.DefaultServiceException;
import com.practice.boxuserservice.repository.users.UsersRepository;
import com.practice.boxuserservice.service.users.aws.S3Service;
import com.practice.boxuserservice.service.users.aws.dto.S3Dto;
import com.practice.boxuserservice.service.users.dto.PostUsersDto;
import com.practice.boxuserservice.service.users.dto.PostUsersResultDto;
import com.practice.boxuserservice.service.users.dto.UpdateUsersIconDto;
import com.practice.boxuserservice.service.users.dto.UpdateUsersThemeDto;
import com.practice.boxuserservice.service.users.dto.UpdateUsersUrlListDto;
import com.practice.boxuserservice.service.users.dto.UserMyPageDto;
import com.practice.boxuserservice.service.users.dto.UserProfileDto;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UsersService.
 *
 * @author : middlefitting
 * @since : 2023/08/24
 */
@Service
@AllArgsConstructor
public class UsersService {

  private final EnvUtil envUtil;
  private final UsersRepository usersRepository;
  private final ModelMapper modelMapper;

  private S3Service s3Service;

  @Transactional
  public PostUsersResultDto saveUser(PostUsersDto dto) {
    if (dto.getNickname() != null) {
      Optional<UsersEntity> duplicateUser = usersRepository.findByNickname(dto.getNickname());
      if (duplicateUser.isPresent()) {
        PostUsersResultDto resultDto = modelMapper.map(duplicateUser.get(),
            PostUsersResultDto.class);
        resultDto.setStatus(HttpStatus.OK);
        return resultDto;
      }
    }
    S3Dto s3Dto = s3Service.uploadDefaultImage();
    String profileImagePath = s3Dto.getPath();
    String profileImageUrl = s3Dto.getUrl();
    try {
      dto.setProfileImagePath(profileImagePath);
      dto.setProfileImageUrl(profileImageUrl);
      UsersEntity user = createUserFromPostDto(dto);
      usersRepository.save(user);
      PostUsersResultDto resultDto = modelMapper.map(user, PostUsersResultDto.class);
      resultDto.setStatus(HttpStatus.CREATED);
      return resultDto;
    } catch (Exception e) {
      s3Service.deleteUserProfileFileFromS3(profileImagePath);
      throw new DefaultServiceException("users.error.user-create-failed", envUtil);
    }
  }

  public UserMyPageDto getUserByNickname(String nickname) {
    UsersEntity users = findUsersByNickname(nickname);
    return modelMapper.map(users, UserMyPageDto.class);
  }

  public UserMyPageDto getUserByUuid(String uuid) {
    UsersEntity users = findUsersByUuid(uuid);
    return modelMapper.map(users, UserMyPageDto.class);
  }

  public UserProfileDto getUserProfileByUuid(String uuid) {
    UsersEntity users = findUsersByUuid(uuid);
    return modelMapper.map(users, UserProfileDto.class);
  }

  public void updateUserUrlList(UpdateUsersUrlListDto updateDto) {
    UsersEntity users = findUsersByUuid(updateDto.getUuid());
    users.updateUrlList(updateDto.getUrlList());
    usersRepository.save(users);
  }

  public void updateUserIcon(UpdateUsersIconDto updateDto) {
    UsersEntity users = findUsersByUuid(updateDto.getUuid());
    users.updateIcon(updateDto.getIcon());
    usersRepository.save(users);
  }

  private void duplicateNicknameCheck(PostUsersDto dto) {
    if (dto.getNickname() != null) {
      Optional<UsersEntity> duplicateUser = usersRepository.findByNickname(dto.getNickname());
      if (duplicateUser.isPresent()) {
        throw new DefaultServiceException("users.error.duplicate-nickname", envUtil);
      }
    }
  }

  private UsersEntity findUsersByNickname(String nickname) {
    return usersRepository.findByNickname(nickname)
        .orElseThrow(() -> new DefaultServiceException("users.error.not-found", envUtil));
  }

  private UsersEntity findUsersByUuid(String uuid) {
    return usersRepository.findByUuid(uuid)
        .orElseThrow(() -> new DefaultServiceException("users.error.not-found", envUtil));
  }

  private UsersEntity createUserFromPostDto(PostUsersDto dto) {
    return UsersEntity.builder()
        .nickname(dto.getNickname())
        .role(dto.getRole())
        .campusId(dto.getCampusId())
        .cursusId(dto.getCursusId())
        .profileImagePath(dto.getProfileImagePath())
        .profileImageUrl(dto.getProfileImageUrl())
        .build();
  }

  public void updateUserTheme(UpdateUsersThemeDto updateDto) {
    UsersEntity users = findUsersByUuid(updateDto.getUuid());
    users.updateTheme(updateDto.getTheme());
    usersRepository.save(users);
  }
}

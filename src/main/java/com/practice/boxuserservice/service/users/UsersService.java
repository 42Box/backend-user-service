package com.practice.boxuserservice.service.users;

import com.practice.boxuserservice.entity.users.UsersEntity;
import com.practice.boxuserservice.global.env.EnvUtil;
import com.practice.boxuserservice.global.exception.DefaultServiceException;
import com.practice.boxuserservice.repository.users.UsersRepository;
import com.practice.boxuserservice.service.users.dto.PostUsersDto;
import com.practice.boxuserservice.service.users.dto.UserMyPageDto;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * UsersService.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/24
 */
@Service
@AllArgsConstructor
public class UsersService {

  private final EnvUtil envUtil;
  private final UsersRepository usersRepository;
  private final ModelMapper modelMapper;

  public void saveUser(PostUsersDto dto) {
    duplicateNicknameCheck(dto);
    try {
      UsersEntity user = createUserFromPostDto(dto);
      usersRepository.save(user);
    } catch (Exception e) {
      throw new DefaultServiceException("users.error.user-create-failed", envUtil);
    }
  }

  private void duplicateNicknameCheck(PostUsersDto dto) {
    System.out.println(dto.getNickname());
    if (dto.getNickname() != null) {
      Optional<UsersEntity> duplicateUser = usersRepository.findByNickname(dto.getNickname());
      if (duplicateUser.isPresent()) {
        throw new DefaultServiceException("users.error.duplicate-nickname", envUtil);
      }
    }
  }

  private UsersEntity createUserFromPostDto(PostUsersDto dto) {
    return UsersEntity.builder()
        .nickname(dto.getNickname())
        .role(dto.getRole())
        .campusId(dto.getCampusId())
        .cursusId(dto.getCursusId())
        .build();
  }

  public UserMyPageDto getUserByNickname(String nickname) {
    UsersEntity users = usersRepository.findByNickname(nickname)
        .orElseThrow(() -> new DefaultServiceException("users.error.not-found", envUtil));
    return modelMapper.map(users, UserMyPageDto.class);
  }
}

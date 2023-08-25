package com.practice.boxuserservice.repository.users;

import com.practice.boxuserservice.entity.users.UsersEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UsersRepository.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/24
 */
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

  Optional<UsersEntity> findByNickname(String nickname);
}

package com.practice.boxuserservice.repository.my_scripts;

import com.practice.boxuserservice.entity.scripts.MyScriptsEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * MyScriptsRepository.
 *
 * @author : middlefitting
 * @since : 2023/08/31
 */
public interface MyScriptsRepository extends JpaRepository<MyScriptsEntity, Long> {

  Optional<MyScriptsEntity> findByIdAndUserUuid(Long savedId, String userUuid);

  Optional<MyScriptsEntity> findByUserUuidAndPath(String userUuid, String path);

  Optional<List<MyScriptsEntity>> findAllByUserUuid(String userUuid);
}

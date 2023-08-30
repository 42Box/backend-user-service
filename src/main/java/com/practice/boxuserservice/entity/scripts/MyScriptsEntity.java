package com.practice.boxuserservice.entity.scripts;

import com.practice.boxuserservice.entity.BaseEntity;
import com.practice.boxuserservice.service.my_scripts.dto.PutMyScriptsDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ScriptsEntity.
 *
 * @author : middlefitting
 * @since : 2023/08/30
 */
@Entity
@Table(
    name = "my_scripts",
    uniqueConstraints = @UniqueConstraint(columnNames = {"my_script_user_uuid", "my_script_path"}),
    indexes = {
        @Index(name = "idx_my_script_user_uuid", columnList = "my_script_user_uuid", unique = false),
        @Index(name = "idx_my_script_path", columnList = "my_script_path", unique = false)
    })
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyScriptsEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "my_script_id", updatable = false)
  private long id;

  @Column(name = "my_script_name", columnDefinition = "VARCHAR(50)", nullable = false, updatable = true, unique = false)
  private String name;

  @Column(name = "my_script_description", columnDefinition = "VARCHAR(255)", nullable = false, updatable = true, unique = false)
  private String description;

  @Column(name = "my_script_path", columnDefinition = "VARCHAR(255)", nullable = false, updatable = false, unique = false)
  private String path;

  @Column(name = "my_script_user_uuid", columnDefinition = "VARCHAR(255)", nullable = false, updatable = false, unique = false)
  private String userUuid;

  @Builder
  public MyScriptsEntity(String name, String description, String path, String userUuid) {
    this.name = name;
    this.description = description;
    this.path = path;
    this.userUuid = userUuid;
  }

  public void update(PutMyScriptsDto dto) {
    this.name = dto.getName();
    this.description = dto.getDescription();
  }
}

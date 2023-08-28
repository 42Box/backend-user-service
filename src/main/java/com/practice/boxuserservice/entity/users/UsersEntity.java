package com.practice.boxuserservice.entity.users;

import com.practice.boxuserservice.entity.BaseEntity;
import com.practice.boxuserservice.entity.users.converter.UsersUrlListConverter;
import com.practice.boxuserservice.entity.users.type.UsersDefault;
import com.practice.boxuserservice.entity.users.type.UsersIcon;
import com.practice.boxuserservice.entity.users.type.UsersRole;
import com.practice.boxuserservice.entity.users.type.UsersTheme;
import com.practice.boxuserservice.entity.users.type.UsersUrl;
import com.practice.boxuserservice.global.exception.DefaultServiceException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;

/**
 * UsersEntity.
 *
 * @author : middlefitting
 * @since : 2023/08/23
 */
@Entity
@Table(name = "users", indexes = {
    @Index(name = "idx_users_uuid", columnList = "user_uuid", unique = true),
    @Index(name = "idx_users_nickname", columnList = "user_nickname", unique = true)
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsersEntity extends BaseEntity {

  private static final int NO_NUMBER_VALUE = -1;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", updatable = false)
  private long id;


  @Column(name = "user_uuid", unique = true, nullable = false, updatable = false)
  private String uuid;

  @Column(name = "user_role", nullable = false)
  @Enumerated(EnumType.STRING)
  private UsersRole role;

  @Column(name = "user_cursus_id", nullable = false)
  private int cursusId;

  @Column(name = "user_campus_id", nullable = false)
  private int campusId;

  @Length(min = 1, max = 50)
  @Column(name = "user_nickname", unique = true, nullable = false, updatable = false)
  private String nickname;

  @Enumerated(EnumType.STRING)
  @Column(name = "user_theme", nullable = false)
  private UsersTheme theme;

  @Enumerated(EnumType.STRING)
  @Column(name = "user_icon", nullable = false)
  private UsersIcon icon;

  @Convert(converter = UsersUrlListConverter.class)
  @Column(name = "user_url_list", columnDefinition = "TEXT", nullable = false)
  private List<UsersUrl> urlList;

  @Column(name = "user_profile_image_path", columnDefinition = "VARCHAR(255)", nullable = false, updatable = false)
  private String profileImagePath;

  @Column(name = "user_profile_image_url", columnDefinition = "VARCHAR(255)", nullable = false, updatable = false)
  private String profileImageUrl;

  @Column(name = "user_status_message", columnDefinition = "VARCHAR(100)", nullable = false)
  private String statusMessage;

  @Builder
  public UsersEntity(String nickname, UsersRole role, Integer campusId, Integer cursusId,
      String profileImagePath, String profileImageUrl) {
    this.theme = UsersTheme.DEFAULT;
    this.icon = UsersIcon.DEFAULT;
    this.statusMessage = UsersDefault.USER_STATUS_MESSAGE.getValue();
    initProfileImagePath(profileImagePath);
    initProfileImageUrl(profileImageUrl);
    initRole(role);
    initUuid();
    initCampusId(campusId);
    initCursusId(cursusId);
    initNickname(nickname);
    initUrlList();
    //    profileImage = UsersDefault.USER_PROFILE_IMAGE.getValue();
  }

  private void initProfileImagePath(String profileImagePath) {
    if (profileImagePath == null || profileImagePath.isEmpty()) {
      throw new DefaultServiceException("유저 프로필 이미지는 비어있을 수 없습니다!", 301, HttpStatus.BAD_REQUEST);
    }
    this.profileImagePath = profileImagePath;
  }

  private void initProfileImageUrl(String profileImageUrl) {
    if (profileImageUrl == null || profileImageUrl.isEmpty()) {
      throw new DefaultServiceException("유저 프로필 이미지 URL 은 비어있을 수 없습니다!", 301,
          HttpStatus.BAD_REQUEST);
    }
    this.profileImageUrl = profileImageUrl;
  }


  private void initRole(UsersRole role) {
    if (role == null) {
      throw new DefaultServiceException("유저 권한은 비어있을 수 없습니다!", 301, HttpStatus.BAD_REQUEST);
    }
    this.role = role;
  }

  private void initUuid() {
    this.uuid = UUID.randomUUID().toString();
  }

  private void initCampusId(Integer campusId) {
    this.campusId = Objects.requireNonNullElse(campusId, NO_NUMBER_VALUE);
  }

  private void initCursusId(Integer cursusId) {
    this.cursusId = Objects.requireNonNullElse(cursusId, NO_NUMBER_VALUE);
  }

  private void initNickname(String nickname) {
    this.nickname = Objects.requireNonNullElse(nickname, "user-" + UUID.randomUUID());
  }

  private void initUrlList() {
    this.urlList = Arrays.asList(
        new UsersUrl("home", "https://42box.kr/"),
        new UsersUrl("23Coaltheme", "https://42box.github.io/front-end/"),
        new UsersUrl("loopback", "http://127.0.0.1:3000/"),
        new UsersUrl("Box 42", "https://42box.github.io/front-end/#/box"),
        new UsersUrl("Intra 42", "https://intra.42.fr"),
        new UsersUrl("Jiphyeonjeon", "https://42library.kr"),
        new UsersUrl("42STAT", "https://stat.42seoul.kr/home"),
        new UsersUrl("24Hane", "https://24hoursarenotenough.42seoul.kr"),
        new UsersUrl("80kCoding", "https://80000coding.oopy.io"),
        new UsersUrl("where42", "https://www.where42.kr"),
        new UsersUrl("cabi", "https://cabi.42seoul.io/"),
        new UsersUrl("42gg", "https://42gg.kr/")
    );
  }

  public void updateUrlList(List<UsersUrl> urlList) {
    this.urlList = urlList;
  }

  public void updateIcon(UsersIcon icon) {
    this.icon = icon;
  }

  public void updateTheme(UsersTheme theme) {
    this.theme = theme;
  }

  public void updateStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
  }
}

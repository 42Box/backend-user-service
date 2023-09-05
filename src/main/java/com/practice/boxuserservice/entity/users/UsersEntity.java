package com.practice.boxuserservice.entity.users;

import com.practice.boxuserservice.entity.BaseEntity;
import com.practice.boxuserservice.entity.users.converter.UsersQuickSlotListConverter;
import com.practice.boxuserservice.entity.users.converter.UsersUrlListConverter;
import com.practice.boxuserservice.entity.users.type.QuickSlot;
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

  @Column(name = "user_big_profile_image_path", columnDefinition = "VARCHAR(255)", nullable = false, updatable = false)
  private String bigProfileImagePath;


  @Column(name = "user_status_message", columnDefinition = "VARCHAR(100)", nullable = false)
  private String statusMessage;

  @Convert(converter = UsersQuickSlotListConverter.class)
  @Column(name = "user_quick_slot_list", columnDefinition = "TEXT", nullable = true)
  private List<QuickSlot> quickSlotList;

  @Builder
  public UsersEntity(String nickname, UsersRole role, Integer campusId, Integer cursusId,
      String profileImagePath, String profileImageUrl, String bigProfileImagePath) {
    this.theme = UsersTheme.DEFAULT;
    this.icon = UsersIcon.DEFAULT;
    this.statusMessage = UsersDefault.USER_STATUS_MESSAGE.getValue();
    initProfileImagePath(profileImagePath);
    initProfileImageUrl(profileImageUrl);
    initBigProfileImagePath(bigProfileImagePath);
    initRole(role);
    initUuid();
    initCampusId(campusId);
    initCursusId(cursusId);
    initNickname(nickname);
    initUrlList();
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

  private void initBigProfileImagePath(String bigProfileImagePath) {
    if (bigProfileImagePath == null || bigProfileImagePath.isEmpty()) {
      throw new DefaultServiceException("유저 프로필 이미지는 비어있을 수 없습니다!", 301, HttpStatus.BAD_REQUEST);
    }
    this.bigProfileImagePath = bigProfileImagePath;
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
        new UsersUrl("42Box", "https://42box.kr/"),
        new UsersUrl("23CoalTheme", "https://42box.github.io/front-end/"),
        new UsersUrl("Intra 42", "https://intra.42.fr"),
        new UsersUrl("Jiphyeonjeon", "https://42library.kr"),
        new UsersUrl("42GG", "https://42gg.kr/"),
        new UsersUrl("42STAT", "https://stat.42seoul.kr/home"),
        new UsersUrl("24Hane", "https://24hoursarenotenough.42seoul.kr"),
        new UsersUrl("80kCoding", "https://80000coding.oopy.io"),
        new UsersUrl("Where42", "https://www.where42.kr"),
        new UsersUrl("Cabi", "https://cabi.42seoul.io/")
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

  public void updateQuickSlotList(List<QuickSlot> quickSlotList) {
    this.quickSlotList = quickSlotList;
  }
}

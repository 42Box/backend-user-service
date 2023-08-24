package com.practice.boxuserservice.entity.users.type;

/**
 * UserDefault.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/23
 */
public enum UsersDefault {
  USER_PROFILE_IMAGE("dummy-images.png");

  private final String value;

  UsersDefault(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}

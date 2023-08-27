package com.practice.boxuserservice.entity.users.type;

/**
 * UserDefault.
 *
 * @author : middlefitting
 * @since : 2023/08/23
 */
public enum UsersDefault {
  USER_PROFILE_IMAGE("dummy-images.png"),
  USER_STATUS_MESSAGE("Hello 42Box");

  private final String value;

  UsersDefault(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}

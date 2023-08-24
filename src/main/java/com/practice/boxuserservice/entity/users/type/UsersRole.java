package com.practice.boxuserservice.entity.users.type;

/**
 * UsersRole.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/24
 */
public enum UsersRole {
  ROLE_ADMIN(0),
  ROLE_AUTH_USER(1),
  ROLE_USER(2);

  private final int index;

  UsersRole(int index) {
    this.index = index;
  }

  public int getIndex() {
    return index;
  }
}

package com.practice.boxuserservice.entity.users.type;

/**
 * UsersTheme.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/24
 */
public enum UsersTheme {
  DEFAULT(0);

  private final int index;

  UsersTheme(int index) {
    this.index = index;
  }

  public int getIndex() {
    return index;
  }
}

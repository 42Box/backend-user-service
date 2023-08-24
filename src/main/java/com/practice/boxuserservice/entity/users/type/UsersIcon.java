package com.practice.boxuserservice.entity.users.type;

/**
 * UsersIcon.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/24
 */
public enum UsersIcon {
  DEFAULT("fox", 0);

  private final String value;
  private final int index;

  UsersIcon(String value, int index) {
    this.value = value;
    this.index = index;
  }

  public String getValue() {
    return value;
  }

  public int getIndex() {
    return index;
  }
}

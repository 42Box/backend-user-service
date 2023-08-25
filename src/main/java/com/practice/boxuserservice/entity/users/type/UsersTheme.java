package com.practice.boxuserservice.entity.users.type;

/**
 * UsersTheme.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/24
 */
public enum UsersTheme {
  DEFAULT("gray", 0),
  ORANGE("orange", 1),
  NONE("chanheki", 2);

  private final String value;
  private final int index;

  UsersTheme(String value, int index) {
    this.value = value;
    this.index = index;
  }

  public String getValue() {
    return value;
  }

  public int getIndex() {
    return index;
  }

  public static UsersTheme fromValue(String value) {
    for (UsersTheme icon : UsersTheme.values()) {
      if (icon.getValue().equals(value)) {
        return icon;
      }
    }
    throw new IllegalArgumentException();
  }

  public static UsersTheme fromIndex(int index) {
    for (UsersTheme icon : UsersTheme.values()) {
      if (icon.getIndex() == index) {
        return icon;
      }
    }
    throw new IllegalArgumentException();
  }
}

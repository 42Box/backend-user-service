package com.practice.boxuserservice.entity.users.type;

/**
 * UsersIcon.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/24
 */
public enum UsersIcon {
  DEFAULT("fox", 0),
  BOX("box", 1),
  ICON42("42", 2),
  SITTING_FOX("sitting_fox", 3),
  BOX_OC("box_oc", 4),
  GUN("gun", 5),
  GON("gon", 6),
  GAM("gam", 7),
  LEE("lee", 8);
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

  public static UsersIcon fromValue(String value) {
    for (UsersIcon icon : UsersIcon.values()) {
      if (icon.getValue().equals(value)) {
        return icon;
      }
    }
    throw new IllegalArgumentException();
  }

  public static UsersIcon fromIndex(int index) {
    for (UsersIcon icon : UsersIcon.values()) {
      if (icon.getIndex() == index) {
        return icon;
      }
    }
    throw new IllegalArgumentException();
  }
}

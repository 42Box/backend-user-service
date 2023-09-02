package com.practice.boxuserservice.entity.users.type;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * UsersUrl.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/25
 */
@Data
@AllArgsConstructor
public class QuickSlot {

  @NotEmpty
  private String scriptUuid;
  @NotEmpty
  private String title;
  @NotEmpty
  private String path;
  @NotEmpty
  private String type;
}

package com.practice.boxuserservice.service.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UpdateUsersStatusMessage.
 *
 * @author : middlefitting
 * @since : 2023/08/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUsersStatusMessage {

  private String statusMessage;
  private String uuid;
}

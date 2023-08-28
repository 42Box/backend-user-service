package com.practice.boxuserservice.controller.users.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RequestUpdateUsersStatusMessage.
 *
 * @author : middlefitting
 * @since : 2023/08/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateUsersStatusMessage {

  @NotEmpty
  @Size(min = 1, max = 100)
  private String statusMessage;
}

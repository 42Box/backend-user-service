package com.practice.boxuserservice.controller.users.dto;

import com.practice.boxuserservice.entity.users.type.UsersUrl;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RequestUpdateUsersUrlListDto.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/25
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestUpdateUsersUrlListDto {

  @NotEmpty
  private List<UsersUrl> urlList;
}

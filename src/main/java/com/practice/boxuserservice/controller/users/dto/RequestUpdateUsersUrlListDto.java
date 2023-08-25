package com.practice.boxuserservice.controller.users.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.util.function.Tuple2;

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
  private List<Tuple2<String, String>> urlList;
}

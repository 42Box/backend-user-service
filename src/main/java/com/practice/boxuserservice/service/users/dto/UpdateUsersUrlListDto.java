package com.practice.boxuserservice.service.users.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.util.function.Tuple2;

/**
 * UpdateUsersUrlListDto.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/25
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUsersUrlListDto {

  private List<Tuple2<String, String>> urlList;
}

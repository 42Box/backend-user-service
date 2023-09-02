package com.practice.boxuserservice.service.users.dto;

import com.practice.boxuserservice.entity.users.type.QuickSlot;
import com.practice.boxuserservice.entity.users.type.UsersUrl;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class UpdateUsersQuickSlotListDto {

  private String uuid;
  private List<QuickSlot> quickSlotList;
}

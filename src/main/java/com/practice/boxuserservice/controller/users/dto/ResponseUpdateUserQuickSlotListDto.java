package com.practice.boxuserservice.controller.users.dto;

import com.practice.boxuserservice.entity.users.type.QuickSlot;
import com.practice.boxuserservice.entity.users.type.UsersUrl;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ResponseUpdateUserIconDto.
 *
 * @author : middlefitting
 * @since : 2023/08/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUpdateUserQuickSlotListDto {

  private List<QuickSlot> quickSlotList;
}

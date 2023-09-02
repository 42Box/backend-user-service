package com.practice.boxuserservice.controller.users.dto;

import com.practice.boxuserservice.entity.users.type.QuickSlot;
import java.util.List;
import javax.validation.constraints.NotNull;
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
public class RequestUpdateUserQuickSlotListDto {

  private List<QuickSlot> quickSlotList;
}

package com.practice.boxuserservice.controller.my_scripts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ResponseDeleteMyScriptDto.
 *
 * @author : middlefitting
 * @since : 2023/09/03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDeleteMyScriptDto {

    private Long savedId;
    private String userUuid;
}

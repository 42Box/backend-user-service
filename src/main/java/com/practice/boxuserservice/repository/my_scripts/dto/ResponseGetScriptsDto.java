package com.practice.boxuserservice.repository.my_scripts.dto;

import com.practice.boxuserservice.entity.scripts.MyScriptsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ResponseGetScriptsDto.
 *
 * @author : middlefitting
 * @since : 2023/08/30
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseGetScriptsDto {

    private long savedId;
    private String name;
    private String description;
    private String path;
    private String userUuid;

    public ResponseGetScriptsDto(MyScriptsEntity entity) {
        this.savedId = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.path = entity.getPath();
        this.userUuid = entity.getUserUuid();
    }
}

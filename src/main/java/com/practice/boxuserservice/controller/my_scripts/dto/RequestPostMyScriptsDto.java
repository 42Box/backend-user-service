package com.practice.boxuserservice.controller.my_scripts.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * RequestPostMyScriptsDto.
 *
 * @author : middlefitting
 * @since : 2023/08/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPostMyScriptsDto {

  @NotEmpty
  @Length(min = 1, max = 50)
  private String name;

  @NotEmpty
  @Length(min = 1, max = 255)
  private String description;

  @NotEmpty
  @Length(min = 1, max = 255)
  private String path;
}

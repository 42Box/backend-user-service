package com.practice.boxuserservice.controller.my_scripts;

import com.practice.boxuserservice.service.my_scripts.MyScriptsService;
import com.practice.boxuserservice.service.my_scripts.private_dto.ResponseIsMyScriptDto;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PrivateMyScriptsService.
 *
 * @author : middlefitting
 * @since : 2023/08/31
 */
@RestController
@RequestMapping("/private/users/me/scripts")
@AllArgsConstructor
public class PrivateMyScriptsController {

  private final MyScriptsService myScriptsService;

  @GetMapping("/is-my-scripts")
//  @HeaderAuthCheck
  public ResponseEntity<ResponseIsMyScriptDto> getIsMyScripts(HttpServletRequest request) {
    String userUuid = request.getHeader("uuid");
    String path = request.getHeader("path");
    System.out.println("userUuid = " + userUuid);
    System.out.println("path = " + path);
    long savedId = myScriptsService.getIsMyScripts(userUuid, path);
    ResponseIsMyScriptDto dto = new ResponseIsMyScriptDto(savedId, true);
    return ResponseEntity.status(HttpStatus.OK).body(dto);
  }
}

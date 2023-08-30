package com.practice.boxuserservice.controller.my_scripts;

import com.practice.boxuserservice.controller.my_scripts.dto.RequestPostMyScriptsDto;
import com.practice.boxuserservice.controller.my_scripts.dto.RequestUpdateMyScriptsDto;
import com.practice.boxuserservice.repository.my_scripts.dto.ResponseGetScriptsDto;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

/**
 * MyScriptsController.
 *
 * @author : middlefitting
 * @since : 2023/08/30
 */
public interface MyScriptsController {

  ResponseEntity<List<ResponseGetScriptsDto>> getMyScriptsPage(HttpServletRequest request);

  ResponseEntity<ResponseGetScriptsDto> getMyScripts(HttpServletRequest request, Long savedId);

  ResponseEntity<ResponseGetScriptsDto> createMyScripts(HttpServletRequest request,
      RequestPostMyScriptsDto requestPostMyScriptsDto);

  ResponseEntity<ResponseGetScriptsDto> updateMyScripts(HttpServletRequest request,
      RequestUpdateMyScriptsDto requestUpdateMyScriptsDto, Long savedId);

  ResponseEntity<Void> deleteMyScripts(HttpServletRequest request, Long savedId);
}

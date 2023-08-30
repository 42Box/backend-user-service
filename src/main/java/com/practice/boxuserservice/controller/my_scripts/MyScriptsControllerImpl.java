package com.practice.boxuserservice.controller.my_scripts;

import com.practice.boxuserservice.controller.my_scripts.dto.RequestPostMyScriptsDto;
import com.practice.boxuserservice.controller.my_scripts.dto.RequestUpdateMyScriptsDto;
import com.practice.boxuserservice.global.aop.validate_nickname_header.HeaderAuthCheck;
import com.practice.boxuserservice.repository.my_scripts.dto.ResponseGetScriptsDto;
import com.practice.boxuserservice.service.my_scripts.MyScriptsService;
import com.practice.boxuserservice.service.my_scripts.dto.GetMyScriptsDto;
import com.practice.boxuserservice.service.my_scripts.dto.PostMyScriptsDto;
import com.practice.boxuserservice.service.my_scripts.dto.PutMyScriptsDto;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MyScriptsControllerImpl.
 *
 * @author : middlefitting
 * @since : 2023/08/30
 */
@RestController
@RequestMapping("/users/me/scripts")
@AllArgsConstructor
public class MyScriptsControllerImpl implements MyScriptsController {

  private final MyScriptsService myScriptsService;
  private final ModelMapper modelMapper;

  @Override
  @HeaderAuthCheck
  @GetMapping("")
  public ResponseEntity<List<ResponseGetScriptsDto>> getMyScriptsPage(HttpServletRequest request) {
    String userUuid = request.getHeader("uuid");
    List<ResponseGetScriptsDto> dto = myScriptsService.getMyScriptsPage(userUuid);
    return ResponseEntity.status(HttpStatus.OK).body(dto);
  }

  @Override
  @HeaderAuthCheck
  @GetMapping("/{savedId}")
  public ResponseEntity<ResponseGetScriptsDto> getMyScripts(HttpServletRequest request,
      @PathVariable @NotNull Long savedId) {
    String userUuid = request.getHeader("uuid");
    GetMyScriptsDto getMyScriptsDto = new GetMyScriptsDto(savedId, userUuid);
    ResponseGetScriptsDto dto = myScriptsService.getMyScripts(getMyScriptsDto);
    return ResponseEntity.status(HttpStatus.OK).body(dto);
  }

  @Override
  @HeaderAuthCheck
  @PostMapping("")
  public ResponseEntity<ResponseGetScriptsDto> createMyScripts(HttpServletRequest request,
      @RequestBody @Valid RequestPostMyScriptsDto requestPostMyScriptsDto) {
    String userUuid = request.getHeader("uuid");
    PostMyScriptsDto dto = modelMapper.map(requestPostMyScriptsDto, PostMyScriptsDto.class);
    dto.setUserUuid(userUuid);
    ResponseGetScriptsDto resDto = myScriptsService.createMyScripts(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(resDto);
  }

  @Override
  @HeaderAuthCheck
  @PutMapping("/{savedId}")
  public ResponseEntity<ResponseGetScriptsDto> updateMyScripts(HttpServletRequest request,
      @RequestBody @Valid RequestUpdateMyScriptsDto requestUpdateMyScriptsDto,
      @PathVariable @NotNull Long savedId) {
    String userUuid = request.getHeader("uuid");
    PutMyScriptsDto dto = modelMapper.map(requestUpdateMyScriptsDto, PutMyScriptsDto.class);
    dto.setUserUuid(userUuid);
    ResponseGetScriptsDto resDto = myScriptsService.updateMyScripts(dto);
    return ResponseEntity.status(HttpStatus.OK).body(resDto);
  }

  @Override
  @HeaderAuthCheck
  @DeleteMapping("/{savedId}")
  public ResponseEntity<Void> deleteMyScripts(HttpServletRequest request,
      @PathVariable @NotNull Long savedId) {
    String userUuid = request.getHeader("uuid");
    GetMyScriptsDto getMyScriptsDto = new GetMyScriptsDto(savedId, userUuid);
    myScriptsService.deleteMyScripts(getMyScriptsDto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}

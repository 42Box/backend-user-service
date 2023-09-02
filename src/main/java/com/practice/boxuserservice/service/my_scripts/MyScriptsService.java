package com.practice.boxuserservice.service.my_scripts;

import com.practice.boxuserservice.entity.scripts.MyScriptsEntity;
import com.practice.boxuserservice.global.env.EnvUtil;
import com.practice.boxuserservice.global.exception.DefaultServiceException;
import com.practice.boxuserservice.repository.my_scripts.MyScriptsRepository;
import com.practice.boxuserservice.repository.my_scripts.dto.ResponseGetScriptsDto;
import com.practice.boxuserservice.service.my_scripts.dto.GetMyScriptsDto;
import com.practice.boxuserservice.service.my_scripts.dto.PostMyScriptsDto;
import com.practice.boxuserservice.service.my_scripts.dto.PutMyScriptsDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * MyScriptsService.
 *
 * @author : middlefitting
 * @since : 2023/08/31
 */
@Service
@AllArgsConstructor
public class MyScriptsService {

  private final MyScriptsRepository myScriptsRepository;
  private final EnvUtil envUtil;
  private final ModelMapper modelMapper;

  public ResponseGetScriptsDto getMyScripts(GetMyScriptsDto getMyScriptsDto) {

    MyScriptsEntity myScriptsEntity = myScriptsRepository.findByIdAndUserUuid(
            getMyScriptsDto.getSavedId(), getMyScriptsDto.getUserUuid())
        .orElseThrow(() -> new DefaultServiceException("my-scripts.error.not-found", envUtil));
    ResponseGetScriptsDto responseGetScriptsDto = modelMapper.map(myScriptsEntity,
        ResponseGetScriptsDto.class);
    responseGetScriptsDto.setSavedId(myScriptsEntity.getId());
    return responseGetScriptsDto;
  }

  public ResponseGetScriptsDto createMyScripts(PostMyScriptsDto dto) {
    MyScriptsEntity myScriptsEntity = buildMyScriptsEntity(dto);
    try {
      myScriptsEntity = myScriptsRepository.save(myScriptsEntity);
    } catch (Exception e) {
      throw new DefaultServiceException("my-scripts.error.create-failed", envUtil);
    }
    ResponseGetScriptsDto resDto = modelMapper.map(myScriptsEntity, ResponseGetScriptsDto.class);
    resDto.setSavedId(myScriptsEntity.getId());
    return resDto;
  }

  private MyScriptsEntity buildMyScriptsEntity(PostMyScriptsDto getMyScriptsDto) {
    return MyScriptsEntity.builder()
        .userUuid(getMyScriptsDto.getUserUuid())
        .description(getMyScriptsDto.getDescription())
        .name(getMyScriptsDto.getName())
        .path(getMyScriptsDto.getPath())
        .build();
  }

  public ResponseGetScriptsDto updateMyScripts(PutMyScriptsDto dto) {
    MyScriptsEntity entity = myScriptsRepository.findByUserUuidAndPath(dto.getUserUuid(),
            dto.getPath())
        .orElseThrow(() -> new DefaultServiceException("my-scripts.error.not-found", envUtil));
    entity.update(dto);
    myScriptsRepository.save(entity);
    ResponseGetScriptsDto resDto = modelMapper.map(entity, ResponseGetScriptsDto.class);
    resDto.setSavedId(entity.getId());
    return resDto;
  }

  public void deleteMyScripts(GetMyScriptsDto getMyScriptsDto) {
    MyScriptsEntity myScriptsEntity = myScriptsRepository.findByIdAndUserUuid(
            getMyScriptsDto.getSavedId(), getMyScriptsDto.getUserUuid())
        .orElseThrow(() -> new DefaultServiceException("my-scripts.error.not-found", envUtil));
    myScriptsRepository.delete(myScriptsEntity);
  }

  public List<ResponseGetScriptsDto> getMyScriptsPage(String userUuid) {
    List<MyScriptsEntity> myScriptsEntities = myScriptsRepository.findAllByUserUuid(userUuid)
        .orElseThrow(
            () -> new DefaultServiceException("my-scripts.error.not-found", envUtil));
    if (myScriptsEntities.isEmpty()) {
      throw new DefaultServiceException("my-scripts.error.not-found", envUtil);
    }
    return myScriptsEntities.stream()
        .map(ResponseGetScriptsDto::new).collect(Collectors.toList());
  }

  public long getIsMyScripts(String userUuid, String path) {
    MyScriptsEntity myScriptsEntity = myScriptsRepository.findByUserUuidAndPath(userUuid, path)
        .orElseThrow(() -> new DefaultServiceException("my-scripts.error.not-found", envUtil));
    return myScriptsEntity.getId();
  }
}

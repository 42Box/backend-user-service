package com.practice.boxuserservice.entity.users.converter;

import com.practice.boxuserservice.entity.users.type.UsersUrl;
import com.practice.boxuserservice.global.env.EnvUtil;
import com.practice.boxuserservice.global.exception.DefaultServiceException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * UsersUrlListConverter.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/25
 */
@Converter
@Component
@AllArgsConstructor
public class UsersUrlListConverter implements AttributeConverter<List<UsersUrl>, String> {

  private final EnvUtil envUtil;
  private static final String ELEMENT_SEPARATOR = ";";
  private static final String TUPLE_SEPARATOR = ",";

  @Override
  public String convertToDatabaseColumn(List<UsersUrl> attribute) {
    return attribute.stream()
        .peek(this::validateUserUrl)
        .map(userUrl -> userUrl.getName() + TUPLE_SEPARATOR + userUrl.getUrl())
        .collect(Collectors.joining(ELEMENT_SEPARATOR));
  }

  @Override
  public List<UsersUrl> convertToEntityAttribute(String dbData) {
    return Arrays.stream(dbData.split(ELEMENT_SEPARATOR))
        .map(s -> {
          String[] parts = s.split(TUPLE_SEPARATOR);
          return new UsersUrl(parts[0], parts[1]);
        })
        .collect(Collectors.toList());
  }

  private void validateUserUrl(UsersUrl userUrl) {
    if (userUrl.getName().contains(ELEMENT_SEPARATOR) || userUrl.getName().contains(TUPLE_SEPARATOR)
        ||
        userUrl.getUrl().contains(ELEMENT_SEPARATOR) || userUrl.getUrl()
        .contains(TUPLE_SEPARATOR)) {
      throw new DefaultServiceException("users.error.users-url-list-converter", envUtil);
    }
  }
}

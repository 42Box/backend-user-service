package com.practice.boxuserservice.entity.users.converter;

import com.practice.boxuserservice.entity.users.type.QuickSlot;
import com.practice.boxuserservice.global.env.EnvUtil;
import com.practice.boxuserservice.global.exception.DefaultServiceException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * QuickSlotListConverter.
 *
 * @author : middlefitting
 * @description :
 * @since : 2023/08/25
 */
@Converter
@Component
@AllArgsConstructor
public class UsersQuickSlotListConverter implements AttributeConverter<List<QuickSlot>, String> {

  private final EnvUtil envUtil;
  private static final String ELEMENT_SEPARATOR = "`";
  private static final String TUPLE_SEPARATOR = ";";

  @Override
  public String convertToDatabaseColumn(List<QuickSlot> attribute) {
    if (attribute == null || attribute.isEmpty()) {
      return "";
    }

    return attribute.stream()
        .peek(this::validateUserUrl)
        .map(userUrl -> userUrl.getScriptUuid() + TUPLE_SEPARATOR
            + userUrl.getTitle() + TUPLE_SEPARATOR
            + userUrl.getPath() + TUPLE_SEPARATOR
            + userUrl.getType()
        )
        .collect(Collectors.joining(ELEMENT_SEPARATOR));
  }

  @Override
  public List<QuickSlot> convertToEntityAttribute(String dbData) {
    if (dbData == null || dbData.trim().isEmpty()) {
      return new ArrayList<>();
    }

    return Arrays.stream(dbData.split(ELEMENT_SEPARATOR))
        .map(s -> {
          String[] parts = s.split(TUPLE_SEPARATOR);
          return new QuickSlot(parts[0], parts[1], parts[2], parts[3]);
        })
        .collect(Collectors.toList());
  }

  private void validateUserUrl(QuickSlot userUrl) {
    if (userUrl.getScriptUuid().contains(ELEMENT_SEPARATOR) || userUrl.getScriptUuid()
        .contains(TUPLE_SEPARATOR)
        ||
        userUrl.getTitle().contains(ELEMENT_SEPARATOR) || userUrl.getTitle()
        .contains(TUPLE_SEPARATOR)
        ||
        userUrl.getPath().contains(ELEMENT_SEPARATOR) || userUrl.getPath().contains(TUPLE_SEPARATOR)
        ||
        userUrl.getType().contains(ELEMENT_SEPARATOR) || userUrl.getType()
        .contains(TUPLE_SEPARATOR)) {
      throw new DefaultServiceException("users.error.users-quick-slot-converter", envUtil);
    }
  }
}

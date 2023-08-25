package com.practice.boxuserservice.entity.users;

import com.practice.boxuserservice.global.env.EnvUtil;
import com.practice.boxuserservice.global.exception.DefaultServiceException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

@Converter
@Component
@AllArgsConstructor
public class UsersUrlListConverter implements
    AttributeConverter<List<Tuple2<String, String>>, String> {

  private final EnvUtil envUtil;
  private static final String ELEMENT_SEPARATOR = ";";
  private static final String TUPLE_SEPARATOR = ",";

  @Override
  public String convertToDatabaseColumn(List<Tuple2<String, String>> attribute) {
    return attribute.stream()
        .peek(this::validateTuple)
        .map(tuple -> tuple.getT1() + TUPLE_SEPARATOR + tuple.getT2())
        .collect(Collectors.joining(ELEMENT_SEPARATOR));
  }

  @Override
  public List<Tuple2<String, String>> convertToEntityAttribute(String dbData) {
    return Arrays.stream(dbData.split(ELEMENT_SEPARATOR))
        .map(s -> {
          String[] parts = s.split(TUPLE_SEPARATOR);
          return Tuples.of(parts[0], parts[1]);
        })
        .collect(Collectors.toList());
  }

  private void validateTuple(Tuple2<String, String> tuple) {
    if (tuple.getT1().contains(ELEMENT_SEPARATOR) || tuple.getT1().contains(TUPLE_SEPARATOR) ||
        tuple.getT2().contains(ELEMENT_SEPARATOR) || tuple.getT2().contains(TUPLE_SEPARATOR)) {
      throw new DefaultServiceException("users.error.users-url-list-converter", envUtil);
    }
  }
}
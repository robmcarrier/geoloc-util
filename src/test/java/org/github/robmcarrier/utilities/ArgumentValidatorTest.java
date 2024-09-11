package org.github.robmcarrier.utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ArgumentValidatorTest {

  @ParameterizedTest
  @MethodSource("isValidData")
  void isValid(String arg, boolean isValid) {
    assertEquals(isValid, ArgumentValidator.isValid(arg));
  }

  private static Stream<Arguments> isValidData() {
    return Stream.of(
        Arguments.of("91910", true),
        Arguments.of("919", false),
        Arguments.of("Madison, WI", true),
        Arguments.of("Madison,WI", true),
        Arguments.of("Madison", false)
    );
  }
}
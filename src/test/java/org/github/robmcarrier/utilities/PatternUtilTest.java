package org.github.robmcarrier.utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PatternUtilTest {

  @ParameterizedTest
  @MethodSource("isZipData")
  void isZip(String arg, boolean isZip) {
    assertEquals(isZip, PatternUtil.isZip(arg));
  }

  private static Stream<Arguments> isZipData() {
    return Stream.of(
        Arguments.of("91910", true),
        Arguments.of("1", false),
        Arguments.of("test", false)
    );
  }

  @ParameterizedTest
  @MethodSource("isValidCityStateData")
  void isValidCityState(String arg, boolean isValid) {
    assertEquals(isValid, PatternUtil.isValidCityState(arg));
  }

  private static Stream<Arguments> isValidCityStateData() {
    return Stream.of(
        Arguments.of("49", false),
        Arguments.of("Madison, WI", true),
        Arguments.of("Madison", false),
        Arguments.of("Madison,WI", true)
    );
  }
}
package org.github.robmcarrier.services;

import java.util.stream.Stream;
import org.github.robmcarrier.models.LocationNameOpenWeatherResponse;
import org.github.robmcarrier.models.Param;
import org.github.robmcarrier.services.OpenWeatherMapServiceImpl.REQUEST_TYPE;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class OpenWeatherMapServiceImplIntegrationTest {

  @ParameterizedTest
  @MethodSource("getLocData")
  @Tag("integration")
  void getLoc() {

  }

  public static Stream<Arguments> getLocData() {
    return Stream.of(Arguments.of(new Param(REQUEST_TYPE.LOCATION_NAME, "Paris"),
        new LocationNameOpenWeatherResponse()));
  }


}
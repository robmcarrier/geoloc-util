package org.github.robmcarrier.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import lombok.extern.java.Log;
import org.github.robmcarrier.configuration.ApplicationConfiguration;
import org.github.robmcarrier.models.LocationNameOpenWeatherResponse;
import org.github.robmcarrier.models.OpenWeatherResponse;
import org.github.robmcarrier.models.Param;
import org.github.robmcarrier.models.ZipOpenWeatherResponse;
import org.github.robmcarrier.services.OpenWeatherMapServiceImpl.REQUEST_TYPE;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Log
class OpenWeatherMapServiceImplIntegrationTest {

  @ParameterizedTest
  @MethodSource("getLocData")
  @Tag("integration")
  void getLoc(Param param, OpenWeatherResponse expectedResponse, String message)
      throws IOException, InterruptedException {
    log.info(message);
    ApplicationContext context = new AnnotationConfigApplicationContext(
        ApplicationConfiguration.class);
    OpenWeatherMapService openWeatherMapService = context.getBean(OpenWeatherMapService.class);
    OpenWeatherResponse actualResponse = openWeatherMapService.getLoc(param);

    assertEquals(expectedResponse, actualResponse,
        "Response from OpenWeatherMap doesn't match expected.");
  }

  private static Stream<Arguments> getLocData() {
    return Stream.of(
        Arguments.of(new Param(REQUEST_TYPE.LOCATION_NAME, "Madison, WI"),
            getMadisonResponse(), "Location Name for Madison, WI"),
        Arguments.of(new Param(REQUEST_TYPE.ZIP, "91910"), get91910Response(),
            "Zip code for 91910"),
        Arguments.of(new Param(REQUEST_TYPE.ZIP, "4"), new ZipOpenWeatherResponse(),
            "No data returned when invalid numerical zip."),
        Arguments.of(new Param(REQUEST_TYPE.ZIP, "abc"), new ZipOpenWeatherResponse(),
            "No data returned when invalid alphabetical zip."),
        Arguments.of(new Param(REQUEST_TYPE.LOCATION_NAME, "-44"),
            new LocationNameOpenWeatherResponse(), "No data returned when invalid location name."));
  }

  private static ZipOpenWeatherResponse get91910Response() {
    return new ZipOpenWeatherResponse("91910", "Chula Vista", "32.6371", "-117.0676", "US");
  }

  private static OpenWeatherResponse getMadisonResponse() {
    Map<String, String> localNames = new HashMap<>(20);
    localNames.put("en", "Madison");
    localNames.put("uk", "Медісон");
    localNames.put("zh", "麦迪逊 / 麥迪遜");
    localNames.put("pl", "Madison");
    localNames.put("ru", "Мадисон");
    localNames.put("ta", "மேடிசன்");
    return new LocationNameOpenWeatherResponse("Madison",
        localNames, "43.074761", "-89.3837613", "US", "Wisconsin");
  }
}
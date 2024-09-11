package org.github.robmcarrier;

import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;
import lombok.extern.java.Log;
import org.github.robmcarrier.configuration.ApplicationConfiguration;
import org.github.robmcarrier.models.OpenWeatherResponse;
import org.github.robmcarrier.models.Param;
import org.github.robmcarrier.services.OpenWeatherMapService;
import org.github.robmcarrier.services.OpenWeatherMapServiceImpl.REQUEST_TYPE;
import org.github.robmcarrier.utilities.ArgumentValidator;
import org.github.robmcarrier.utilities.PatternUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Log
public class Main {
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
    OpenWeatherMapService openWeatherMapService = context.getBean(OpenWeatherMapService.class);
    Gson gson = context.getBean(Gson.class);

    List<OpenWeatherResponse> responses = Arrays.stream(args).map(arg -> {
      if (!ArgumentValidator.isValid(arg)) {
        log.info("Following arg is in invalid format: " + arg);
        return null;
      }
      Param param = new Param();
      param.setRequestType(PatternUtil.isZip(arg) ? REQUEST_TYPE.ZIP : REQUEST_TYPE.LOCATION_NAME);
      param.setValue(arg);
      try {
        return openWeatherMapService.getLoc(param);
      } catch (Exception e) {
        log.severe(e.getMessage());
        log.severe("Unable to get location data for " + param.getValue());
        return null;
      }
    }).toList();

    log.info(toString(gson, responses));
  }

  public static String toString(Gson gson, List<OpenWeatherResponse> responses) {
    return gson.toJson(responses);
  }
}
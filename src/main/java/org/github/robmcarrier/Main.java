package org.github.robmcarrier;

import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;
import lombok.extern.java.Log;
import org.github.robmcarrier.models.OpenWeatherResponse;
import org.github.robmcarrier.models.Param;
import org.github.robmcarrier.services.OpenWeatherMapServiceImpl;
import org.github.robmcarrier.services.OpenWeatherMapServiceImpl.REQUEST_TYPE;
import org.github.robmcarrier.utilities.GsonManager;
import org.github.robmcarrier.utilities.PatternUtil;

@Log
public class Main {

  public static void main(String[] args) {
    OpenWeatherMapServiceImpl openWeatherMapService = new OpenWeatherMapServiceImpl();

    List<OpenWeatherResponse> responses = Arrays.stream(args).map(arg -> {
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

    log.info(toString(responses));
  }

  public static String toString(List<OpenWeatherResponse> responses) {
    Gson gson = GsonManager.getInstance();
    return gson.toJson(responses);
  }
}
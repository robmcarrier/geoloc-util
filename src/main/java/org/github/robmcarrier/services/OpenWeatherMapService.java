package org.github.robmcarrier.services;

import java.io.IOException;
import org.github.robmcarrier.models.OpenWeatherResponse;
import org.github.robmcarrier.models.Param;

public interface OpenWeatherMapService {

  OpenWeatherResponse getLoc(Param param)
      throws IOException, InterruptedException;
}

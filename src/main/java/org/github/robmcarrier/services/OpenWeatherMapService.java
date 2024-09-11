package org.github.robmcarrier.services;

import java.io.IOException;
import java.net.URISyntaxException;
import org.github.robmcarrier.models.OpenWeatherResponse;
import org.github.robmcarrier.models.Param;
import org.github.robmcarrier.utilities.OpenWeatherException;

public interface OpenWeatherMapService {

  OpenWeatherResponse getLoc(Param param)
      throws OpenWeatherException, URISyntaxException, IOException, InterruptedException;
}

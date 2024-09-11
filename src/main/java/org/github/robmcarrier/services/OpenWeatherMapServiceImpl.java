package org.github.robmcarrier.services;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import lombok.extern.java.Log;
import org.github.robmcarrier.models.LocationNameOpenWeatherResponse;
import org.github.robmcarrier.models.OpenWeatherResponse;
import org.github.robmcarrier.models.Param;
import org.github.robmcarrier.models.ZipOpenWeatherResponse;
import org.github.robmcarrier.utilities.AppProperties;
import org.github.robmcarrier.utilities.GsonManager;
import org.github.robmcarrier.utilities.HttpClientManager;
import org.springframework.web.util.UriComponentsBuilder;

@Log
public class OpenWeatherMapServiceImpl implements OpenWeatherMapService {

  private static final String URL = "api.openweathermap.org";

  @Override
  public OpenWeatherResponse getLoc(Param param)
      throws IOException, InterruptedException {
    URI uri = getUri(param.getRequestType(), param.getValue());
    return sendRequest(uri, param.getRequestType());
  }

  private URI getUri(REQUEST_TYPE requestType, String value) {
    UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
        .host(URL)
        .scheme("http")
        .path("geo/")
        .path("1.0/");

    if (REQUEST_TYPE.ZIP.equals(requestType)) {
      builder
          .path("zip")
          .queryParam("zip", value + ",US");
    } else {
      builder
          .path("direct")
          .queryParam("q", value)
          .queryParam("limit", "1");
    }
    return builder.queryParam("appid", getApiKey()).build(false).toUri();
  }

  private OpenWeatherResponse sendRequest(URI uri, REQUEST_TYPE requestType)
      throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(uri)
        .GET()
        .build();

    HttpResponse<String> response = HttpClientManager.getInstance()
        .send(request, BodyHandlers.ofString());

    Gson gson = GsonManager.getInstance();
    if (REQUEST_TYPE.LOCATION_NAME.equals(requestType)) {
      return gson.fromJson(response.body(), LocationNameOpenWeatherResponse[].class)[0];
    }
    return gson.fromJson(response.body(), ZipOpenWeatherResponse.class);

  }

  private String getApiKey() {
    AppProperties props = AppProperties.getInstance();
    return props.getProperty(AppProperties.OPEN_WEATHER_MAP_API_KEY);
  }

  public enum REQUEST_TYPE {
    ZIP,
    LOCATION_NAME
  }

}

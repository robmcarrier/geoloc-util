package org.github.robmcarrier.configuration;

import com.google.gson.Gson;
import java.net.http.HttpClient;
import org.github.robmcarrier.services.OpenWeatherMapService;
import org.github.robmcarrier.services.OpenWeatherMapServiceImpl;
import org.github.robmcarrier.utilities.AppProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

  @Bean
  public HttpClient httpClient() {
    return HttpClient.newBuilder().build();
  }

  @Bean
  public AppProperties appProperties() {
    return AppProperties.getInstance();
  }

  @Bean
  public Gson gson() {
    return new Gson();
  }

  @Bean
  public OpenWeatherMapService openWeatherMapService() {
    return new OpenWeatherMapServiceImpl(httpClient(), appProperties(), gson());
  }
}

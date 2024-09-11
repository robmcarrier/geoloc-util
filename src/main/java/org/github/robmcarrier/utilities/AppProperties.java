package org.github.robmcarrier.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import lombok.extern.java.Log;

@Log
public class AppProperties {

  public static final String OPEN_WEATHER_MAP_API_KEY = "openweathermap.api.key";

  private static AppProperties instance = null;
  private final Properties appProps;

  private AppProperties() throws PropFileNotFoundException {
    appProps = new Properties();
    try (InputStream appConfigFileStream = AppProperties.class.getResourceAsStream( "/application.properties")) {
      appProps.load(appConfigFileStream);
    } catch (Exception e) {
      log.severe("Unable to load properties from application.properties");
      log.severe(e.getMessage());
      throw new PropFileNotFoundException();
    }
  }

  public static AppProperties getInstance() {

    if (null == instance) {
      instance = new AppProperties();
    }
    return instance;
  }

  public String getProperty(String propertyKey) {
    return appProps.getProperty(propertyKey);
  }

}

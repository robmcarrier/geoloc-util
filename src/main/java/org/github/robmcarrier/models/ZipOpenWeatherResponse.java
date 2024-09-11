package org.github.robmcarrier.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ZipOpenWeatherResponse implements OpenWeatherResponse {
  private String zip;
  private String name;
  private String lat;
  private String lon;
  private String country;
}

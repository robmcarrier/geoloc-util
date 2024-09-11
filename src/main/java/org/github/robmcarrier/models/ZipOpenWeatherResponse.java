package org.github.robmcarrier.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ZipOpenWeatherResponse implements OpenWeatherResponse {
  private String zip;
  private String name;
  private String lat;
  private String lon;
  private String country;
}

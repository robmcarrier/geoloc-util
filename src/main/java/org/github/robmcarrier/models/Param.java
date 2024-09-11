package org.github.robmcarrier.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.github.robmcarrier.services.OpenWeatherMapServiceImpl.REQUEST_TYPE;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Param {
  private REQUEST_TYPE requestType;
  private String value;
}

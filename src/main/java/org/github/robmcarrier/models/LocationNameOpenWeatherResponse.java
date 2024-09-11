package org.github.robmcarrier.models;

import com.google.gson.annotations.SerializedName;
import java.util.Map;
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
public class LocationNameOpenWeatherResponse implements OpenWeatherResponse {
  private String name;
  @SerializedName("local_names")
  private Map<String, String> localNames;
  private String lat;
  private String lon;
  private String country;
  private String state;
}

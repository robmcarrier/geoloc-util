package org.github.robmcarrier.models;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LocationNameResponse implements Response{
  private String name;
  private Map<String, String> localNames;
  private String lat;
  private String lon;
  private String country;
  private String state;
}

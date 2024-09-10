package org.github.robmcarrier.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ZipResponse implements Response{
  private String zip;
  private String name;
  private String lat;
  private String lon;
  private String country;
}

package org.github.robmcarrier.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {

  private PatternUtil() {
  }

  public static boolean isZip(String arg) {
    Pattern pattern = Pattern.compile("^\\d{5}(?:[-\\s]\\d{4})?$");
    Matcher matcher = pattern.matcher(arg);
    return matcher.find();
  }

  public static boolean isValidCityState(String arg) {
    Pattern pattern = Pattern.compile("^[A-Za-z\\s]+,\\s?[A-Z]{2}$");
    Matcher matcher = pattern.matcher(arg);
    return matcher.find();
  }
}

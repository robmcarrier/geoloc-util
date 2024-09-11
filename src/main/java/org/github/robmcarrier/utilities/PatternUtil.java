package org.github.robmcarrier.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {

  private PatternUtil() {}
  public static boolean isZip(String param) {
    Pattern pattern = Pattern.compile("^\\d{5}(?:[-\\s]\\d{4})?$");
    Matcher matcher = pattern.matcher(param);
    return matcher.find();

  }
}

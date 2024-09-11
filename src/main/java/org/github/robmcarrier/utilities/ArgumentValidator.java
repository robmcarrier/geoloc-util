package org.github.robmcarrier.utilities;

public class ArgumentValidator {

  private ArgumentValidator() {
  }

  public static boolean isValid(String arg) {
    return PatternUtil.isZip(arg) || PatternUtil.isValidCityState(arg);
  }
}

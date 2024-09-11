package org.github.robmcarrier.utilities;

public class ArgumentValidator {

  public static boolean isValid(String arg) {
    return PatternUtil.isZip(arg) || PatternUtil.isValidState(arg);
  }
}

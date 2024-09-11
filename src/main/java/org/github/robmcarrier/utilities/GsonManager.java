package org.github.robmcarrier.utilities;

import com.google.gson.Gson;

public class GsonManager {
  private static Gson gson = null;

  private GsonManager() {
  }

  public static Gson getInstance() {
    if (null == gson){
      gson = new Gson();
    }
    return gson;
  }
}

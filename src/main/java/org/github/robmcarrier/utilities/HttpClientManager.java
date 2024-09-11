package org.github.robmcarrier.utilities;

import java.net.http.HttpClient;

public class HttpClientManager {

  private static HttpClient httpClient = null;

  private HttpClientManager() {
  }

  public static HttpClient getInstance() {
    if (null == httpClient) {
      httpClient = HttpClient.newBuilder().build();
    }
    return httpClient;
  }
}

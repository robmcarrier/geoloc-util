package org.github.robmcarrier.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.stream.Stream;
import org.github.robmcarrier.models.LocationNameOpenWeatherResponse;
import org.github.robmcarrier.models.OpenWeatherResponse;
import org.github.robmcarrier.models.Param;
import org.github.robmcarrier.models.ZipOpenWeatherResponse;
import org.github.robmcarrier.services.OpenWeatherMapServiceImpl.REQUEST_TYPE;
import org.github.robmcarrier.utilities.AppProperties;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class OpenWeatherMapServiceImplTest {

  @Mock
  private HttpClient httpClient;

  @Mock
  private AppProperties appProperties;

  @Mock
  private Gson gson;

  @InjectMocks
  private OpenWeatherMapServiceImpl service;

  @BeforeEach
  public void init() {
    MockitoAnnotations.openMocks(this);
    when(appProperties.getProperty(any())).thenReturn("test");
  }

  @SuppressWarnings("unchecked")
  private String setupHttpClient() throws IOException, InterruptedException {
    HttpResponse<String> mockedResponse = (HttpResponse<String>) mock(HttpResponse.class);
    String mockedResponseBody = Instancio.create(String.class);
    when(mockedResponse.body()).thenReturn(mockedResponseBody);
    when(httpClient.send(any(HttpRequest.class), eq(BodyHandlers.ofString()))).thenReturn(
        mockedResponse);
    return mockedResponseBody;
  }

  private <T extends Throwable> void setupHttpClientThrowException(Class<T> exceptionClazz)
      throws InstantiationException, IllegalAccessException, IOException, InterruptedException, NoSuchMethodException, InvocationTargetException {
    when(httpClient.send(any(), eq(BodyHandlers.ofString()))).thenThrow(
        exceptionClazz.getDeclaredConstructor().newInstance());
  }

  @Test
  void getLocZip() throws IOException, InterruptedException {
    String mockedResponseBody = setupHttpClient();
    ZipOpenWeatherResponse expectedResp = Instancio.create(ZipOpenWeatherResponse.class);

    when(gson.fromJson(mockedResponseBody,
        ZipOpenWeatherResponse.class)).thenReturn(expectedResp);
    Param param = new Param(REQUEST_TYPE.ZIP, "Test");

    OpenWeatherResponse actualResp = service.getLoc(param);
    assertEquals(expectedResp, actualResp);
    verifyMocks(ZipOpenWeatherResponse.class);
  }

  @Test
  void getLocLocationName() throws IOException, InterruptedException {
    String mockedResponseBody = setupHttpClient();
    LocationNameOpenWeatherResponse[] responses = Instancio.createList(
        LocationNameOpenWeatherResponse.class).toArray(new LocationNameOpenWeatherResponse[0]);
    when(gson.fromJson(mockedResponseBody,
        LocationNameOpenWeatherResponse[].class)).thenReturn(responses);
    Param param = new Param(REQUEST_TYPE.LOCATION_NAME, "Test");

    OpenWeatherResponse actualResp = service.getLoc(param);
    assertEquals(responses[0], actualResp);
    verifyMocks(LocationNameOpenWeatherResponse[].class);
  }

  @ParameterizedTest
  @MethodSource("getLocExceptionData")
  <T extends Throwable> void getLocException(Class<T> exceptionClazz)
      throws IOException, InterruptedException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
    setupHttpClientThrowException(exceptionClazz);
    try {
      service.getLoc(Instancio.create(Param.class));
    } catch (Exception e) {
      assertEquals(e.getClass(), exceptionClazz);
    }
  }

  private static Stream<Arguments> getLocExceptionData() {
    return Stream.of(
        Arguments.of(IOException.class),
        Arguments.of(InterruptedException.class)
    );
  }


  private <T> void verifyMocks(Class<T> clazz) throws IOException, InterruptedException {
    verify(appProperties).getProperty(AppProperties.OPEN_WEATHER_MAP_API_KEY);
    verify(httpClient).send(any(), eq(BodyHandlers.ofString()));
    verify(gson).fromJson(anyString(), eq(clazz));
  }
}
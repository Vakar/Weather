package space.vakar.weather.provider.openweather.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import space.vakar.weather.provider.openweather.api.Retriever;
import space.vakar.weather.provider.openweather.exceptions.WeatherParserException;
import space.vakar.weather.provider.openweather.exceptions.WeatherRetrieverException;
import space.vakar.weather.provider.openweather.model.CurrentWeather;
import space.vakar.weather.provider.openweather.testutils.CurrentWeatherPopulator;

@RunWith(MockitoJUnitRunner.class)
public class ParserTest {

  @Mock
  private Retriever retriverMock;

  private ParserImpl parser;

  private CurrentWeather expectedWeather;

  private InputStream weatherStream;
  private InputStream notValidWeatherStream;

  @Before
  public void setUpParser() {
    parser = new ParserImpl(retriverMock);
  }

  @Before
  public void setUpWeather() {
    expectedWeather = new CurrentWeather();
    CurrentWeatherPopulator.populateData(expectedWeather);

  }

  @Before
  public void setUpInputStreams() throws IOException {
    ClassLoader loader = getClass().getClassLoader();
    weatherStream = loader.getResource("weather.xml").openStream();
    notValidWeatherStream = loader.getResource("notValidWeather.xml").openStream();
  }

  @Test
  public void shouldReturnCorrectObject()
      throws WeatherParserException, WeatherRetrieverException {
    when(retriverMock.weatherXml(1)).thenReturn(weatherStream);
    assertEquals(expectedWeather, parser.weather(1));
  }

  @Test(expected = WeatherParserException.class)
  public void shouldThrowOpenWeatherParserException_WhenParsingNotValidXml()
      throws WeatherRetrieverException, WeatherParserException {
    when(retriverMock.weatherXml(1)).thenReturn(notValidWeatherStream);
    parser.weather(1);
  }
}

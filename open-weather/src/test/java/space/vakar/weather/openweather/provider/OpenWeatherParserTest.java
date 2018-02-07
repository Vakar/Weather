package space.vakar.weather.openweather.provider;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.time.LocalDateTime;

import org.junit.Test;

import space.vakar.weather.domain.model.temperature.TemperatureUnit;
import space.vakar.weather.openweather.model.City;
import space.vakar.weather.openweather.model.Clouds;
import space.vakar.weather.openweather.model.Coordinates;
import space.vakar.weather.openweather.model.CurrentWeather;
import space.vakar.weather.openweather.model.Humidity;
import space.vakar.weather.openweather.model.LastUpdate;
import space.vakar.weather.openweather.model.Precipitation;
import space.vakar.weather.openweather.model.Pressure;
import space.vakar.weather.openweather.model.Sun;
import space.vakar.weather.openweather.model.Temperature;
import space.vakar.weather.openweather.model.Visibility;
import space.vakar.weather.openweather.model.Weather;
import space.vakar.weather.openweather.model.Wind;
import space.vakar.weather.openweather.model.WindDirection;
import space.vakar.weather.openweather.model.WindSpeed;
import space.vakar.weather.openweather.provider.OpenWeatherParser;

public class OpenWeatherParserTest {
	
	private OpenWeatherParser weatherParser = new OpenWeatherParser();
	
	@Test
	public void shouldReturnCorrectObject() throws Exception {
		ClassLoader loader = getClass().getClassLoader();
		InputStream inputStream = loader.getResource("weather.xml").openStream();
		assertEquals(getExpectedWeather(), weatherParser.parse(inputStream));		
	}
	
	private CurrentWeather getExpectedWeather() {
		CurrentWeather weather = new CurrentWeather();		
		weather.setCity(getCity());
		weather.setTemperature(new Temperature(261.15, 261.15, 261.15, TemperatureUnit.KELVIN));
		weather.setHumidity(new Humidity(85, "%"));
		weather.setPressure(new Pressure(1002, "hPa"));		
		weather.setWind(getWind());
		weather.setClouds(new Clouds(90, "overcast clouds"));
		weather.setVisibility(new Visibility(4828));
		weather.setPrecipitation(new Precipitation(13.4, "snow"));
		weather.setWeather(new Weather(600, "light snow", "13n"));
		weather.setLastupdate(getLastUpdate());
		return weather;
	}
	
	private City getCity() {
		LocalDateTime rise = LocalDateTime.parse("2018-01-31T11:42:29");
		LocalDateTime set = LocalDateTime.parse("2018-01-31T21:23:30");
		Sun sun = new Sun(rise, set);
		Coordinates coordinates = new Coordinates(-64.8, 46.1);
		return new City(6076211, "Moncton", "CA", sun, coordinates);
	}
	
	private Wind getWind() {
		WindSpeed speed = new WindSpeed(7.7, "Moderate breeze");
		WindDirection direction = new WindDirection(290, "WNW", "West-northwest");
		return new Wind(speed, direction);
	}
	
	private LastUpdate  getLastUpdate() {
		return new LastUpdate(LocalDateTime.parse("2018-01-31T08:27:00"));
	}
}
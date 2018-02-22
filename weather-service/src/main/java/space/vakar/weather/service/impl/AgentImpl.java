package space.vakar.weather.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

import space.vakar.weather.domain.api.WeatherProvider;
import space.vakar.weather.domain.model.Weather;
import space.vakar.weather.provider.openweather.Provider;
import space.vakar.weather.service.api.Agent;
import space.vakar.weather.service.api.WeatherContainer;

public class AgentImpl implements Agent {

  private WeatherProvider provider = new Provider();
  private WeatherContainer container = new Container();
  
  private static final Duration TWO_HOURS = Duration.ofHours(2);  

  public AgentImpl() {
    
  }

  public AgentImpl(WeatherProvider provider, WeatherContainer container) {
    setProvider(provider);
    setContainer(container);
  }

  @Override
  public Weather weather(int cityId) {
    Weather weather = container.pull(cityId);
      return isValide(weather)? weather : askProviderAndCash(cityId);
  }

  private boolean isValide(Weather weather) {
    return weather != null && isFresh(weather);
  }
  
  @Override
  public boolean isFresh(Weather weather) {
    Duration lastUpdateDelta = Duration.between(weather.getLastUpdate(), LocalDateTime.now());
    return lastUpdateDelta.compareTo(TWO_HOURS) < 0? true : false;
}

  private Weather askProviderAndCash(int cityId) {    
    Weather weather = null;
    try {
      weather = provider.provideWeather(cityId);
      container.push(weather, cityId);
    } catch (Exception e) {
      throw new WeatherServiceException("Can't get weather from provider cause" + e.getMessage(), e);
    }        
    return weather;
  }

  public WeatherProvider getProvider() {
    return provider;
  }

  public void setProvider(WeatherProvider provider) {
    this.provider = provider;
  }

  public WeatherContainer getContainer() {
    return container;
  }

  public void setContainer(WeatherContainer container) {
    this.container = container;
  }

  @Override
  public int hashCode() {
    return Objects.hash(provider, container);
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof AgentImpl) {
      AgentImpl that = (AgentImpl) object;
      return Objects.equals(this.provider, that.provider)
          && Objects.equals(this.container, that.container);
    }
    return false;
  }

  @Override
  public String toString() {
    String format = "AgentImpl [provider=%s, container=%s]";
    return String.format(format, provider, container);
  }
}

package space.vakar.weather.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.validation.ValidationException;
import space.vakar.weather.domain.api.WeatherProvider;
import space.vakar.weather.domain.model.Weather;
import space.vakar.weather.domain.util.BeanValidator;
import space.vakar.weather.service.api.Agent;
import space.vakar.weather.service.api.WeatherContainer;

public class AgentImpl implements Agent {

  private WeatherProvider provider;
  private WeatherContainer container;
  
  private static final Duration TWO_HOURS = Duration.ofHours(2);

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
      validate(weather);
      container.push(weather, cityId);
    } catch (Exception e) {
      throw new WeatherServiceException("Can't get weather from provider", e);
    }        
    return weather;
  }

  private void validate(Weather weather) throws ValidationException {
    if (BeanValidator.validate(weather).size() > 0) {
      throw new ValidationException(weather.getClass().getName() + " isn't valid");
    }
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

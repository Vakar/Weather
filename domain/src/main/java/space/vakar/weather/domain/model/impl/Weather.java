package space.vakar.weather.domain.model.impl;

import java.time.LocalDateTime;
import java.util.Objects;

import space.vakar.weather.domain.model.impl.atmosphere.Atmosphere;
import space.vakar.weather.domain.model.impl.location.Location;
import space.vakar.weather.domain.model.impl.temperature.Temperature;
import space.vakar.weather.domain.model.impl.wind.Wind;

public class Weather extends DomainObject {

  private Wind wind;

  private Location location;

  private Atmosphere atmosphere;

  private Temperature temperature;

  private LocalDateTime lastUpdate;

  public Weather() {
    super();
  }

  /**
   * Constructor.
   */
  public Weather(Wind wind, Location location, Atmosphere atmosphere, Temperature temperature,
      LocalDateTime lastUpdate) {
    super();
    this.wind = wind;
    this.location = location;
    this.atmosphere = atmosphere;
    this.temperature = temperature;
    this.lastUpdate = lastUpdate;
  }

  public Wind getWind() {
    return wind;
  }

  public void setWind(Wind wind) {
    this.wind = wind;
  }

  public Atmosphere getAtmosphere() {
    return atmosphere;
  }

  public void setAtmosphere(Atmosphere atmosphere) {
    this.atmosphere = atmosphere;
  }

  public Temperature getTemperature() {
    return temperature;
  }

  public void setTemperature(Temperature temperature) {
    this.temperature = temperature;
  }

  public LocalDateTime getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(LocalDateTime lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, wind, location, atmosphere, temperature, lastUpdate);
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof Weather) {
      Weather that = (Weather) object;
      return this.id == that.id && Objects.equals(this.wind, that.wind)
          && Objects.equals(this.location, that.location)
          && Objects.equals(this.atmosphere, that.atmosphere)
          && Objects.equals(this.temperature, that.temperature)
          && Objects.equals(this.lastUpdate, that.lastUpdate);
    }
    return false;
  }

  @Override
  public String toString() {
    String format =
        "Weather [id=%s, wind=%s, location=%s, atmosphere=%s, " + "temperature=%s, lastUpdate=%s]";
    return String.format(format, id, wind, location, atmosphere, temperature, lastUpdate);
  }
}

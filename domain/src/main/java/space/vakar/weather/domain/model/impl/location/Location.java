package space.vakar.weather.domain.model.impl.location;

import java.util.Objects;

import space.vakar.weather.domain.model.impl.DomainObject;

public class Location extends DomainObject {

  private Coordinates coordinates;

  private Sun sun;

  private City city;

  private String country;

  public Location() {

  }

  /**
   * Constructor.
   * 
   */
  public Location(Coordinates coordinates, Sun sun, City city, String country) {
    super();
    this.coordinates = coordinates;
    this.sun = sun;
    this.city = city;
    this.country = country;
  }

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  public Sun getSun() {
    return sun;
  }

  public void setSun(Sun sun) {
    this.sun = sun;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, coordinates, sun, city, country);
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof Location) {
      Location that = (Location) object;
      return this.id == that.id && Objects.equals(this.coordinates, that.coordinates)
          && Objects.equals(this.sun, that.sun) && Objects.equals(this.city, that.city)
          && Objects.equals(this.country, that.country);
    }
    return false;
  }

  @Override
  public String toString() {
    String format = "Location [id=%s, coord=%s, sun=%s, city=%s, country=%s]";
    return String.format(format, id, coordinates, sun, city, country);
  }
}

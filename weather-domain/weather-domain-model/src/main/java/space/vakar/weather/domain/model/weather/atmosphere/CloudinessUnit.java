package space.vakar.weather.domain.model.weather.atmosphere;

public enum CloudinessUnit {
  PERCENTAGE_OF_SKY_AREA("%");
  private final String shortName;

  CloudinessUnit(String shortName) {
    this.shortName = shortName;
  }

  public String getShortName() {
    return this.shortName;
  }
}

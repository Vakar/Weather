package space.vakar.weather.domain.model.weather.wind;

import space.vakar.weather.domain.model.weather.AbstractJavaBeanTest;

public class WindDirectionTest extends AbstractJavaBeanTest<WindDirection> {

  @Override
  protected WindDirection getBeanInstance() {
    return new WindDirection();
  }
}
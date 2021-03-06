package space.vakar.open.weather.persistence;

import java.io.IOException;
import java.util.Properties;

import lombok.Getter;
import lombok.Setter;
import space.vakar.weather.domain.util.PropertiesUtil;

@Getter
@Setter
class JdbcConnectionProperties extends PropertiesUtil {

  private static JdbcConnectionProperties instance;

  private static final String PROPERTIES_FILE = "/connection.properties";
  private static final String DRIVER_PROPERTY_NAME = "driver";
  private static final String URL_PROPERTY_NAME = "url";
  private static final String USER_PROPERTY_NAME = "user";
  private static final String PSW_PROPERTY_NAME = "pass";

  private String driver;
  private String url;
  private String user;
  private String psw;

  private JdbcConnectionProperties() {
    Properties properties;
    try {
      properties = readPropertiesFromFile(PROPERTIES_FILE);
    } catch (IOException exp) {
      throw new DatasourceException(exp.getMessage(), exp);
    }
    this.driver = properties.getProperty(DRIVER_PROPERTY_NAME);
    this.url = properties.getProperty(URL_PROPERTY_NAME);
    this.user = properties.getProperty(USER_PROPERTY_NAME);
    this.psw = properties.getProperty(PSW_PROPERTY_NAME);
  }

  static JdbcConnectionProperties getInstance() {
    if (instance == null) {
      instance = new JdbcConnectionProperties();
    }
    return instance;
  }
}

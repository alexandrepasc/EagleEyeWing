package com.eagleeye.wing.common;

import java.io.InputStream;
import java.util.Properties;

public class Configuration {

  public String getFirebaseUrl() {

    try (InputStream input = Configuration.class.getClassLoader().getResourceAsStream("config.properties")) {

      Properties properties = new Properties();

      if (input == null) {
        return null;
      }

      properties.load(input);

      return properties.getProperty("firebase.url");

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public String getFirebaseKey() {
    try (InputStream input = Configuration.class.getClassLoader().getResourceAsStream("config.properties")) {

      Properties properties = new Properties();

      if (input == null) {
        return null;
      }

      properties.load(input);

      return properties.getProperty("firebase.key");

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}

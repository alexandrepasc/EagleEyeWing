package com.eagleeye.wing.exceptions;

import java.util.UUID;

public class FeederNotFoundException extends RuntimeException {

  public FeederNotFoundException(UUID id) {
    super("Feeder not found, id: " + id);
  }
}

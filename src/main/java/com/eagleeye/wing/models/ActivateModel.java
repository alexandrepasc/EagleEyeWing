package com.eagleeye.wing.models;

import java.util.List;
import java.util.UUID;

public class ActivateModel {

  private List<UUID> ids;

  public ActivateModel() {
  }

  public List<UUID> getIds() {
    return ids;
  }

  public void setIds(List<UUID> ids) {
    this.ids = ids;
  }
}

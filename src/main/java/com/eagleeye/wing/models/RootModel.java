package com.eagleeye.wing.models;

import java.util.List;

public class RootModel {

  private NotificationModel notification;
  private DataModel data;
  private String to;
  private List<String> registration_ids;

  public NotificationModel getNotification() {
    return notification;
  }

  public void setNotification(NotificationModel notification) {
    this.notification = notification;
  }

  public DataModel getData() {
    return data;
  }

  public void setData(DataModel data) {
    this.data = data;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public List<String> getRegistration_ids() {
    return registration_ids;
  }

  public void setRegistration_ids(List<String> registration_ids) {
    this.registration_ids = registration_ids;
  }
}

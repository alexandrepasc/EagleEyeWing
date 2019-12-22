package com.eagleeye.wing.models;

public class RootModel {

  private NotificationModel notification;
  private DataModel data;
  private String to;

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
}

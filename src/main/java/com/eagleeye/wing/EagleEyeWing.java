package com.eagleeye.wing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EagleEyeWing {

  public static void main(String[] args) {

    /*NotificationModel notificationModel = new NotificationModel();
    notificationModel.setTitle("Test sever");
    notificationModel.setBody("testing server side");

    DataModel dataModel = new DataModel();
    //dataModel.setTitle("Test server");
    //dataModel.setDetail("yoh yoh");

    RootModel rootModel = new RootModel();
    rootModel.setNotification(notificationModel);
    rootModel.setData(dataModel);
    rootModel.setTo("eE_EDdQGbq4:APA91bFYQX_7uCOPZb9ABU8lpqJTbzYndjB-Gu3TdodYmeLcLzYqH63pKk4dlxZE7KnuuTXOpTVqe1kLf1dHIjZu_H1UM2m1uq7wFrD1zJ28dLzC6hcY6mq9jSxGUSdYFucLt2rOtvN8");



    Configuration config = new Configuration();
    System.out.println(config.getFirebaseUrl());
    System.out.println(config.getFirebaseKey());

    FireBaseService fireBaseService = new FireBaseService();
    String response = fireBaseService.sendNotification(rootModel);
    System.out.println(response);*/

    SpringApplication.run(EagleEyeWing.class);
  }
}

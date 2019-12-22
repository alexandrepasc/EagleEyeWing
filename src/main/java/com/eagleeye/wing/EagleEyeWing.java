package com.eagleeye.wing;

import com.eagleeye.wing.models.DataModel;
import com.eagleeye.wing.models.NotificationModel;
import com.eagleeye.wing.models.RootModel;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EagleEyeWing {

  public static void main(String[] args)
      throws IOException {

    NotificationModel notificationModel = new NotificationModel();
    notificationModel.setTitle("Test sever");
    notificationModel.setBody("testing server side");

    DataModel dataModel = new DataModel();
    //dataModel.setTitle("Test server");
    //dataModel.setDetail("yoh yoh");

    RootModel rootModel = new RootModel();
    rootModel.setNotification(notificationModel);
    rootModel.setData(dataModel);
    //rootModel.setTo("dgART6R3qu0:APA91bFgq8ebjQ4kFod-niDpMGSQAP2wV3MoK4afWMGb0zc"
        //+ "-lYgDGF9AZCtdtLizRQopRbc6fvxYxHolfaan5H0LR9ZMRSeaw4FuhstVOdMS76cdyaep5GxdNX0p9wvVMnt5cIGg-MQx");

    List<String> registerList = new ArrayList<>();
    registerList.add("dgART6R3qu0:APA91bFgq8ebjQ4kFod-niDpMGSQAP2wV3MoK4afWMGb0zc-lYgDGF9AZCtdtLizRQopRbc6fvxYxHolfaan5H0LR9ZMRSeaw4FuhstVOdMS76cdyaep5GxdNX0p9wvVMnt5cIGg-MQx");
    rootModel.setRegistration_ids(registerList);

    String json = new ObjectMapper().writeValueAsString(rootModel);

    System.out.println(json);


    CloseableHttpClient client = HttpClients.createDefault();

    HttpPost httpPost = new HttpPost("https://fcm.googleapis.com/fcm/send");

    httpPost.addHeader("Content-Type", "application/json");
    httpPost.addHeader("Authorization", "key=AAAADrmYT2Q:APA91bF"
        + "-Jfoqo5oaXqsYRWfovLnBuNLkStYw3YrV7WYYDWVFMS8uQsX73NlTF5iJNXAEoCEQWM9QMUO5MFFJf3Ov71OWt8hbeqe6QYLx5_0IzQv8x_we5dAwtR47GlTJ8Ke_gDrH-jjn");

    StringEntity stringEntity = new StringEntity(json);
    httpPost.setEntity(stringEntity);

    CloseableHttpResponse response = client.execute(httpPost);

    ResponseHandler<String> handler = new BasicResponseHandler();
    String body = handler.handleResponse(response);

    System.out.println(body);
  }
}

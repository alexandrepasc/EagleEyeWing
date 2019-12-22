package com.eagleeye.wing.services;

import com.eagleeye.wing.common.Configuration;
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
import java.util.HashMap;
import java.util.Map;

public class FireBaseService {

  public String sendNotification(RootModel rootModel) {

    try {

      Configuration config = new Configuration();

      Map<String, String> header = getHeader(config.getFirebaseKey());

      String json = new ObjectMapper().writeValueAsString(rootModel);
      StringEntity body = new StringEntity(json);

      String response = apiPost(
          config.getFirebaseUrl(),
          header,
          body
      );

      return response;

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private Map<String, String> getHeader(String token) {

    Map<String, String> header = new HashMap<>();

    header.put("Content-Type", "application/json");
    header.put("Authorization", "key=" + token);

    return header;
  }

  private String apiPost(String url, Map<String, String> header, StringEntity body)
    throws IOException {

    CloseableHttpClient client = HttpClients.createDefault();

    HttpPost httpPost = new HttpPost(url);

    for (String key : header.keySet()) {
      httpPost.addHeader(key, header.get(key));
    }

    httpPost.setEntity(body);

    CloseableHttpResponse response = client.execute(httpPost);

    ResponseHandler<String> handler = new BasicResponseHandler();
    String responseBody = handler.handleResponse(response);

    client.close();

    return responseBody;
  }
}

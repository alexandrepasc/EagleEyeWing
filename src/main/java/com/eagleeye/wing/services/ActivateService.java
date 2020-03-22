package com.eagleeye.wing.services;

import com.eagleeye.wing.dao.FeederDao;
import com.eagleeye.wing.exceptions.FeederNotFoundException;
import com.eagleeye.wing.models.ActivateModel;
import com.eagleeye.wing.models.DataModel;
import com.eagleeye.wing.models.FeederModel;
import com.eagleeye.wing.models.NotificationModel;
import com.eagleeye.wing.models.RootModel;
import com.eagleeye.wing.models.TokenModel;
import com.eagleeye.wing.models.UserModel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ActivateService {

  @Autowired
  private FeederDao feederDao;

  public String activate(ActivateModel activateModel) {

    for (UUID id : activateModel.getIds()) {

      Optional<FeederModel> feederValidation = feederDao.findById(id);
      if (!feederValidation.isPresent()) {
        throw new FeederNotFoundException(id);
      }

      FeederModel feeder = feederValidation.get();

      RootModel rootModel = buildNotification(feeder);

      if (rootModel.getRegistration_ids().size() > 0) {
        FireBaseService fireBaseService = new FireBaseService();

        String response = fireBaseService.sendNotification(rootModel);

        System.out.println(response);
      } else {
        System.out.println("no users");
      }
    }

    try {
      System.out.println("sleep");
      Thread.sleep(10000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return "ok";
  }

  private RootModel buildNotification(FeederModel feeder) {

    RootModel rootModel = new RootModel();

    NotificationModel notificationModel = new NotificationModel();
    notificationModel.setTitle(feeder.getPackName() + " updated");
    notificationModel.setBody("New version " + feeder.getPackVersion());

    rootModel.setNotification(notificationModel);

    DataModel dataModel = new DataModel();
    dataModel.setTitle(feeder.getPackName() + " updated");
    dataModel.setBody("New version " + feeder.getPackVersion());
    dataModel.setId(feeder.getId().toString());
    dataModel.setClick_action("FLUTTER_NOTIFICATION_CLICK");

    rootModel.setData(dataModel);

    List<String> sendTo = new ArrayList<>();

    for (UserModel user : feeder.getUsers()) {

      if (user.getDevices() != null) {

        List<TokenModel> tokenList = new ArrayList<>();
        try {
          tokenList = JsonToObject(user.getDevices());
        } catch (JsonProcessingException e) {
          e.printStackTrace();
        }

        for (TokenModel token : tokenList) {
          sendTo.add(token.getToken());
        }
      }
    }

    rootModel.setRegistration_ids(sendTo);

    return rootModel;
  }

  private List<TokenModel> JsonToObject(String json)
    throws JsonProcessingException {

    if (json != null) {
      ObjectMapper mapper = new ObjectMapper()
          .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

      TokenModel[] mapping = mapper.readValue(json, TokenModel[].class);

      List<TokenModel> devices = Arrays.asList(mapping);

      return devices;
    } else {
      return null;
    }
  }
}

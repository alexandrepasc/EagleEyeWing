package com.eagleeye.wing.services;

import com.eagleeye.wing.dao.FeederDao;
import com.eagleeye.wing.exceptions.FeederNotFoundException;
import com.eagleeye.wing.models.ActivateModel;
import com.eagleeye.wing.models.FeederModel;
import com.eagleeye.wing.models.UserModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

      for (UserModel user : feeder.getUsers()) {

        System.out.println(user.getUsername());
      }

    }

    return "ok";
  }
}

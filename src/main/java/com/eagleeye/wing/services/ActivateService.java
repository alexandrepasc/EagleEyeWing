package com.eagleeye.wing.services;

import com.eagleeye.wing.models.ActivateModel;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ActivateService {

  public String activate(ActivateModel activateModel) {

    for (UUID id : activateModel.getIds()) {
      System.out.println(id);
    }

    return "ok";
  }
}

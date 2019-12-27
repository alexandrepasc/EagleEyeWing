package com.eagleeye.wing.controllers;

import com.eagleeye.wing.models.ActivateModel;
import com.eagleeye.wing.services.ActivateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activate")
public class ActivateController {

  @Autowired
  private ActivateService activateService;

  @PostMapping("")
  public ResponseEntity<?> activate(@RequestBody ActivateModel activateModel) {

    return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).body(activateService.activate(activateModel));
  }
}

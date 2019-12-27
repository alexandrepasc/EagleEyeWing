package com.eagleeye.wing.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(FeederNotFoundException.class)
  public void userNotFound(HttpServletResponse response) throws IOException {
    response.sendError(HttpStatus.NOT_FOUND.value());
  }
}

package com.eagleeye.wing.controllers;

import com.eagleeye.wing.configurations.JwtTokenUtil;
import com.eagleeye.wing.services.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping("")
public class AuthenticationController {

  @Autowired
  AuthenticationService authenticationService;

  @PostMapping("/auth")
  public ResponseEntity<?> auth(@RequestBody AuthenticationModel auth) {

    return ResponseEntity.status(HttpStatus.OK.value()).body(authenticationService.authUser(auth));
  }
}

class AuthenticationModel implements Serializable {

  private static final long serialVersionUID = 5926468583005150707L;

  private String username;
  private String password;

  public AuthenticationModel() {
  }

  public AuthenticationModel(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}

@Service
class AuthenticationService {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private AuthService authService;

  public AuthenticationResponse authUser(AuthenticationModel auth) {

    authenticate(auth.getUsername(), auth.getPassword());

    final UserDetails userDetails = authService.loadUserByUsername(auth.getUsername());
    if (userDetails == null) {
      return null;
    }

    final String token = jwtTokenUtil.generateToken(userDetails);

    return new AuthenticationResponse(token);
  }

  private void authenticate(String username, String password) {

    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
  }
}

class AuthenticationResponse implements Serializable {

  private static final long serialVersionUID = -8091879091924046844L;

  private final String token;

  public AuthenticationResponse(String jwttoken) {
    this.token = jwttoken;
  }

  public String getToken() {
    return token;
  }
}

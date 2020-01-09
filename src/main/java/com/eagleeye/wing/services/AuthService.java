package com.eagleeye.wing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AuthService implements UserDetailsService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  private String username = "ear";

  private String password = "3agle3ye3ar";
  //private String password = "$2a$10$aAmb8x3toBKp82ZJbuNEqeYf2/xvtHB8YLGCkeD1pRQPQ.VPzn3WG";

  private String[] roles = { "ROLE_ADMIN" };

  public AuthService() {
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return passwordEncoder.encode(this.password);
  }

  public String[] getRoles() {
    return roles;
  }

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {

    if (!username.equals(getUsername())) {
      return null;
    }

    return new User(getUsername(), getPassword(), getAuthorities(getRoles()));
  }

  private static Collection<? extends GrantedAuthority> getAuthorities(String[] roles) {
    Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roles);
    return authorities;
  }
}

package com.blockbyblock.springtemp.Session;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path = "/api/v1/session")
public class SessionRestController {

  @GetMapping
  public String uid(HttpSession session) {
      return session.getId();
  }
}

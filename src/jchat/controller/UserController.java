package jchat.controller;

import jchat.entity.User;
import jchat.service.UserExistsException;
import jchat.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

  @Autowired
  UserRegisterService userRegisterService;

  @PostMapping("/register")
  ResponseEntity registerUser(@RequestBody User user) {
    try {
      user = userRegisterService.createNewUser(user);
    } catch (UserExistsException e) {
      return ResponseEntity.status(409).body(e.getMessage());
    }
    return ResponseEntity.status(201).body(user);
  }
}

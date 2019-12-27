package jchat.controller;

import jchat.entity.User;
import jchat.exception.EmailTakenException;
import jchat.exception.UserNameTakenException;
import jchat.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

  @Autowired
  UserRegisterService userRegisterService;

  @PostMapping("/register")
  ResponseEntity registerUser(@Valid @RequestBody User user) throws UserNameTakenException, EmailTakenException {
      user = userRegisterService.createNewUser(user);
    return ResponseEntity.status(201).body(user);
  }
}

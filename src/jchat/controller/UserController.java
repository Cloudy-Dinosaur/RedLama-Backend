package jchat.controller;

import jchat.entity.User;
import jchat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

  @Autowired UserRepository userRepository;

  @PostMapping("/create")
  User createUser(@RequestBody User user) {
    userRepository.save(user);
    return user;
  }
}

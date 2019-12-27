package jchat.service;

import jchat.entity.User;
import jchat.exception.EmailTakenException;
import jchat.exception.UserNameTakenException;
import jchat.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserRegisterService {

  private static final int SALT_LENGTH = 10;

  @Autowired UserRepository userRepository;

  /**
   * Create a new User
   *
   * @param user user object to save in the database
   * @return returns the saved user object
   * @throws UserNameTakenException throws when username or email is taken
   */
  public User createNewUser(User user) throws UserNameTakenException, EmailTakenException {
    if (user == null) {
      throw new NullPointerException();
    }
    if (!doesUserExist(user)) {
      user.setPasswordSalt(generatePasswordSalt());
      String saltedPassword = saltPassword(user.getPassword(), user.getPasswordSalt());
      user.setPassword(hashPassword(saltedPassword));
      return saveUserInDatabase(user);
    } else {
      return null;
    }
  }

  /**
   * Checks if User is already registered with this email or username
   *
   * @param user user to check
   * @return returns true if a user has already used the username or email
   */
  private boolean doesUserExist(User user) throws UserNameTakenException, EmailTakenException {
    if (isUserNameTaken(user.getUserName())) {
      throw new UserNameTakenException("Username is taken");
    } else if (isEmailAddressTaken(user.getEmailAddress())) {
      throw new EmailTakenException("Email is in use");
    } else {
      return false;
    }
  }

  /**
   * Checks In the database if the username is already taken
   *
   * @param userName username to search for
   * @return returns true if username is taken; returns false if its not taken
   */
  private boolean isUserNameTaken(String userName) {
    ArrayList<User> usersFound = userRepository.findUserByUserName(userName);
    return usersFound.size() > 0;
  }

  /**
   * Checks In the database if the email is already in use
   *
   * @param emailAddress email to search for
   * @return returns true if the emailAddress is taken; returns false if its not taken
   */
  private boolean isEmailAddressTaken(String emailAddress) {
    ArrayList<User> usersFound = userRepository.findUserByEmailAddress(emailAddress);
    return usersFound.size() > 0;
  }

  /**
   * Saves the user into the database
   *
   * @param user user object to save
   * @return returns saved object
   */
  private User saveUserInDatabase(User user) {
    return userRepository.save(user);
  }

  /**
   * Combines salts the password, so its harder to brute force
   *
   * @param password password to salt
   * @param salt salt to use
   * @return returns salted password
   */
  private String saltPassword(String password, String salt) {
    return password + salt;
  }

  /**
   * Converts password into a sha-256 hash and then into hex code This will prevent leaking the
   * passwords
   *
   * @param password password to hash
   * @return returns hashed password
   */
  private String hashPassword(String password) {
    return DigestUtils.sha256Hex(password);
  }

  /**
   * Generates a salt(random characters)
   *
   * @return returns this salt
   */
  private String generatePasswordSalt() {
    return RandomString.make(SALT_LENGTH);
  }
}

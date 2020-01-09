package jchat.controller;

import jchat.exception.EmailTakenException;
import jchat.exception.UserNameTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionController {
    @ExceptionHandler ( value = UserNameTakenException.class)
    public ResponseEntity<Object> userNameTakenExceptionHandler (UserNameTakenException userNameTakenException){
        return new ResponseEntity<>("User Name is Taken", HttpStatus.valueOf(409));
    }

    @ExceptionHandler ( value = EmailTakenException.class)
    public ResponseEntity<Object> userNameTakenException (EmailTakenException emailTakenException){
        return new ResponseEntity<>("Email is Taken", HttpStatus.valueOf(409));
    }

}

package jchat.exception;

public class UserNameTakenException extends Exception {
    public UserNameTakenException(String errorMessage){
        super(errorMessage);
    }
}

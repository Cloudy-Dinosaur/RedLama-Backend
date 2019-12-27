package jchat.exception;

public class EmailTakenException extends Exception {
    public EmailTakenException(String errorMessage){
        super(errorMessage);
    }
}

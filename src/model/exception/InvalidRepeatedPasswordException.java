package model.exception;

public class InvalidRepeatedPasswordException extends Exception{
    public InvalidRepeatedPasswordException(String message){
        super(message);
    }
}

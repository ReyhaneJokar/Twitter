package model.exception;

public class InvalidPhoneNumberException extends Exception{
    public InvalidPhoneNumberException(String message){
        super(message);
    }
}

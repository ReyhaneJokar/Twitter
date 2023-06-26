package model.exception;

public class InvalidEmailFormatException extends Exception{
    public InvalidEmailFormatException(String message){
        super(message);
    }
}

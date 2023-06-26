package client.resources;

import model.exception.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfoVerifier {
    /**
     * checks the user id validity
     * id can have only english characters and numbers
     */
    public static void checkUserIdValidity(String id) throws InvalidUsernameException {
        int size = id.length();
        //format pattern for the id
        Matcher matcher = Pattern.compile("[a-zA-Z0-9]+!*$").matcher(id);
        if(!matcher.find()){
            //if matcher did not find anything, it means its invalid
            throw new InvalidUsernameException("ID must have only english characters and numbers.");
        }
        if(size < 5){
            throw new InvalidUsernameException("ID must have at least 5 characters.");
        }

    }

    /**
     * checks the user's password validity
     */
    public static void checkPasswordValidity(String password) throws InvalidPasswordException {
        int size = password.length();
        //format pattern of password
        Matcher matcher = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])").matcher(password);
        if(!matcher.find()){
            //means invalid format
            throw new InvalidPasswordException("Password must have at least an uppercase and a lowercase and a number.");
        }
        if(size < 8){
            //means has less than 8 number
            throw new InvalidPasswordException("Password must have at least 8 characters.");
        }

    }

    /**
     * check the repeated password validity
     */
    public static void checkRepeatedPasswordValidity(String password , String repeated) throws InvalidRepeatedPasswordException {
        if (!password.equals(repeated)){
            throw new InvalidRepeatedPasswordException("Repeated password is wrong.");
        }
    }

    /**
     * checks the email validity
     */
    public static void checkEmailValidity(String email)throws InvalidEmailFormatException {
        if (email.trim().length() != 0){
            //regex to check the validity
            Matcher matcher = Pattern.compile(".+@gmail.com$").matcher(email);
            if(!matcher.find()){
                throw new InvalidEmailFormatException("Email must have a valid domain like @gmail.com");
            }
        }
    }

    /**
     * checks the phone number validity
     */
    public static void checkPhoneNumberValidity(String phoneNumber) throws InvalidPhoneNumberException {
        if (phoneNumber.trim().length() != 0){
            int size = phoneNumber.length();
            Matcher matcher = Pattern.compile("^09.+").matcher(phoneNumber);
            if(!matcher.find()){
                throw new InvalidPhoneNumberException("Phone number must start with 09...");
            }
            if(size != 11){
                throw new InvalidPhoneNumberException("Phone number must have exactly 11 characters.");
            }
        }
    }

    public static void checkNameAndLastname(String name , String lastName) throws InvalidInputFormatException {
        if (name.trim().length() == 0 || lastName.trim().length() == 0){
            throw new InvalidInputFormatException("You must enter your name and last name.");
        }
    }

    public static void checkBirthDateValidity(String birthDate) throws InvalidBirthDateException {
        if (birthDate.length() != 8){
            throw new InvalidBirthDateException("Birth date must have exactly 8 characters.");
        }
        String[] dates = birthDate.split("/");
        if (dates.length != 3){
            throw new InvalidBirthDateException("Wrong birth date format");
        }
        for (int i = 0; i < 3; i++) {
            Matcher matcher = Pattern.compile("[0-9]+!*$").matcher(dates[i]);
            if(!matcher.find()){
                throw new InvalidBirthDateException("Wrong birth date format");
            }
        }
    }
}

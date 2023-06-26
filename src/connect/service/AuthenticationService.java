package connect.service;

import model.Config;
import model.Response;
import model.request.Authentication.LogInReq;
import model.request.Authentication.SignUpReq;
import model.user.User;

import java.io.*;

public class AuthenticationService {

    private final Config config;

    public AuthenticationService() {
        config = Config.getInstance();
    }

    public Response signup(SignUpReq request){
        User user = new User(request.getSenderId() , request.getName() , request.getLastname() , request.getEmail(), request.getPhone(), request.getPassword(), request.getCountry(), request.getBirthdate());

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)) {
            while (fileInputStream.available() > 0){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(user.getId())){
                    return new Response(request.getSenderId(), false , "This id is used before!");
                }
                else if(readUser.getEmail().equals(user.getEmail())){
                    return new Response(request.getSenderId(), false , "This email is used before!");
                }
                else if(readUser.getPhone().equals(user.getPhone())){
                    return new Response(request.getSenderId(), false , "This phone number is used before!");
                }
            }

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

//        user.getProfile().setAvatar(new Image("pic/profile.png"));
//        user.getProfile().setHeader(new Image("pic/header.blue2.png"));
        user.getProfile().setLocation(request.getCountry());
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(config.getFILE_NAME() , true))) {
            out.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Response(request.getSenderId(), true , "User signed up successfully!");
    }

    public Response login(LogInReq request){

        User user = new User(request.getSenderId() , request.getPassword());

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)) {

            while (fileInputStream.available() > 0){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(user.getId())){
                    if (readUser.getPassword().equals(user.getPassword())){
                        return new Response(request.getSenderId() , true , "User logged in successfully!");
                    }
                    else {
                        return new Response(request.getSenderId(), false , "wrong password!" );
                    }
                }
            }

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return new Response(request.getSenderId(), false , "User not found!");
    }
}

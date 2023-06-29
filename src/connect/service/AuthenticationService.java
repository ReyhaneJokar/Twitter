package connect.service;

import model.Config;
import model.Response;
import model.request.Authentication.LogInReq;
import model.request.Authentication.SignUpReq;
import model.tweet.Tweet;
import model.user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationService {

    private final Config config;

    public AuthenticationService() {
        config = Config.getInstance();
    }

    public Response signup(SignUpReq request){
        User user = new User(request.getSenderId() , request.getName() , request.getLastname() , request.getEmail(), request.getPhone(), request.getPassword(), request.getCountry(), request.getBirthdate());

        ArrayList<User> allUsers = new ArrayList<>();

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
                allUsers.add(readUser);
            }

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        user.getProfile().setAvatar(null);
        user.getProfile().setHeader(null);
        user.getProfile().setLocation(request.getCountry());
        allUsers.add(user);

        //for test

        Tweet tweet = new Tweet("hello " , null, new Date() , user);
        user.getTweets().add(tweet);
        Tweet tweet1 = new Tweet("bye " , null, new Date() , user);
        user.getTweets().add(tweet);


//        User user2 = new User("curlyrey" , "reyhan" , "jokar" , "rey@gmail.com" , null , "jkR138237" , "Iran" , null);
//        user.getFollowers().add(user2);
//        user2.getFollowing().add(user);

        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(config.getFILE_NAME()))) {
            for (int i = 0; i < allUsers.size(); i++) {
                out.writeObject(allUsers.get(i));
            }
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

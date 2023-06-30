package connect.service;

import model.Config;
import model.Response;
import model.exception.TweetNotFoundException;
import model.request.tweet.*;
import model.tweet.Tweet;
import model.user.User;

import java.io.*;
import java.util.ArrayList;

public class TweetService {

    private final Config config;

    public TweetService() {
        config = Config.getInstance();
    }

    public Response tweet(TweetReq request){
        ArrayList<User> allUsers = new ArrayList<>();

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)){
            while (fileInputStream.available() > 0){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(request.getSenderId())){
                    readUser.getTweets().add(new Tweet(request.getUuid() , request.getText() , request.getImage() , request.getDate() , readUser));
                }
                allUsers.add(readUser);
            }
        } catch (IOException | ClassNotFoundException e) {
            return new Response(request.getSenderId(), false , e.getMessage());
        }

        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(config.getFILE_NAME()))) {
            for (int i = 0; i < allUsers.size(); i++) {
                out.writeObject(allUsers.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
            return new Response(request.getSenderId(), false , e.getMessage());
        }
        return new Response(request.getSenderId(), true , "Tweet added successfully.");

    }

    public Response retweet(RetweetReq request){

        ArrayList<User> allUsers = new ArrayList<>();

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)){
            while (fileInputStream.available() > 0){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(request.getSenderId())){
                    readUser.getTweets().add(new Tweet(request.getUuid() , request.getText() , request.getImage() , request.getDate() , request.getUser()));
                }
                else if (readUser.getId().equals(request.getUser().getId())){
                    for (int i = 0; i < readUser.getTweets().size(); i++) {
                        if (readUser.getTweets().get(i).getUuid().equals(request.getUuid())){
                            readUser.getTweets().get(i).setRetweets(readUser.getTweets().get(i).getRetweets()+1);
                            break;
                        }
                    }
                }
                allUsers.add(readUser);
            }
        } catch (IOException | ClassNotFoundException e) {
            return new Response(request.getSenderId(), false , e.getMessage());
        }

        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(config.getFILE_NAME()))) {
            for (int i = 0; i < allUsers.size(); i++) {
                out.writeObject(allUsers.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
            return new Response(request.getSenderId(), false , e.getMessage());
        }
        return new Response(request.getSenderId(), true , "Tweet retweeted successfully.");
    }

    public Response quote(QuoteReq request){

        //create a quote tweet with new uuid
        //find the user and set it

        ArrayList<User> allUsers = new ArrayList<>();

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)){
            boolean flag = false;
            while (fileInputStream.available() > 0){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(request.getUser().getId())){
                    for (int i = 0; i < readUser.getTweets().size(); i++) {
                        if (readUser.getTweets().get(i).getUuid().equals(request.getUuid())){
                            readUser.getTweets().get(i).setRetweets(readUser.getTweets().get(i).getRetweets()+1);
                            flag = true;
                            break;
                        }
                    }
                }
                allUsers.add(readUser);
            }
            if (!flag){
                throw new TweetNotFoundException();
            }
        } catch (IOException | ClassNotFoundException e) {
            return new Response(request.getSenderId(), false , e.getMessage());
        } catch (TweetNotFoundException e) {
            return new Response(request.getSenderId(), false , "tweet not found");
        }

        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(config.getFILE_NAME()))) {
            for (int i = 0; i < allUsers.size(); i++) {
                out.writeObject(allUsers.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
            return new Response(request.getSenderId(), false , e.getMessage());
        }

        return new Response(request.getSenderId() , true , "Tweet quoted successfully");
    }

    public Response like(LikeTweetReq request){
        ArrayList<User> allUsers = new ArrayList<>();

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)){
            while (fileInputStream.available() > 0){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(request.getSenderId())){
                    for (int i = 0; i < readUser.getTweets().size(); i++) {
                        if (readUser.getTweets().get(i).getUuid().equals(request.getUuid())){
                            readUser.getTweets().get(i).setLikes(readUser.getTweets().get(i).getLikes()+1);
                            break;
                        }
                    }
                }
                allUsers.add(readUser);
            }
        } catch (IOException | ClassNotFoundException e) {
            return new Response(request.getSenderId(), false , e.getMessage());
        }

        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(config.getFILE_NAME()))) {
            for (int i = 0; i < allUsers.size(); i++) {
                out.writeObject(allUsers.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
            return new Response(request.getSenderId(), false , e.getMessage());
        }
        return new Response(request.getSenderId(), true , "Tweet liked successfully.");
    }

    public Response reply(ReplyReq request){
        
        User sender = null;
        User target = null;

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)){
            while (fileInputStream.available() > 0){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(request.getSenderId())){
                    sender = readUser;
                }
                else if(readUser.getId().equals(request.getTargetId())){
                    target = readUser;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            return new Response(request.getSenderId(), false , e.getMessage());
        }
        
        Tweet targetTweet = new Tweet(request.getText() , request.getImage() , request.getLikes() , request.getRetweets() , request.getDate() , request.getReplies() , target , request.getUuid());
        Tweet replyTweet = new Tweet(request.getReplyText() , null , request.getReplyLikes() , request.getReplyRetweets() , request.getReplyDate() , request.getReplyReplies() , sender , request.getReplyuuid());

        ArrayList<User> allUsers = new ArrayList<>();

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)){
            boolean flag = false;
            while (fileInputStream.available() > 0){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(request.getTargetId())){
                    for (int i = 0; i < readUser.getTweets().size(); i++) {
                        if (readUser.getTweets().get(i).equals(targetTweet)){
                            readUser.getTweets().get(i).getReplies().add(replyTweet);
                            flag = true;
                            break;
                        }
                    }
                }
                allUsers.add(readUser);
            }
            if (!flag){
                throw new TweetNotFoundException();
            }
        } catch (TweetNotFoundException e) {
            return new Response(request.getSenderId(), false , "Tweet not found.");
        }
        catch (IOException | ClassNotFoundException e) {
            return new Response(request.getSenderId(), false , e.getMessage());
        }

        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(config.getFILE_NAME()))) {
            for (int i = 0; i < allUsers.size(); i++) {
                out.writeObject(allUsers.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
            return new Response(request.getSenderId(), false , e.getMessage());
        }
        return new Response(request.getSenderId(), true , "Tweet replied successfully.");
    }
}

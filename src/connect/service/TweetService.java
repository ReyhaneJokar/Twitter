package connect.service;

import model.Config;
import model.Response;
import model.exception.TweetNotFoundException;
import model.request.tweet.QuoteReq;
import model.request.tweet.ReplyReq;
import model.request.tweet.RetweetReq;
import model.request.tweet.TweetReq;
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
                    readUser.getTweets().add(new Tweet(request.getText() , request.getImage() , request.getDate()));
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

        return null;
    }

    public Response quote(QuoteReq request){

        return null;
    }

    public Response reply(ReplyReq request){

        Tweet targetTweet = new Tweet(request.getText() , request.getImage() , request.getLikes() , request.getRetweets() , request.getDate() , request.getReplies());
        Tweet replyTweet = new Tweet(request.getReplyText() , request.getReplyImage() , request.getReplyLikes() , request.getReplyRetweets() , request.getReplyDate() , request.getReplyReplies());

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
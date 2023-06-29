package connect.service;

import model.Config;
import model.Response;
import model.exception.ConfigNotFoundException;
import model.exception.UserNotFoundException;
import model.request.user.*;
import model.response.*;
import model.tweet.Tweet;
import model.user.User;

import java.io.*;
import java.util.ArrayList;

public class UserService {

    private final Config config;

    public UserService() {
        config = Config.getInstance();
    }

    public Response get_profile(MyProfileReq request){
        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)) {
            boolean flag = false;
            while (fileInputStream.available() > 0){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(request.getSenderId())){
                    flag = true;
                    return new GetUserProfileRes(request.getSenderId(), true , "profile sent" , readUser.getId() , readUser.getName(), readUser.getCountry() , readUser.getJoinDate(), readUser.getProfile(), readUser.getFollowers() , readUser.getFollowing() , readUser.getTweets());
                }
            }
            if (!flag){
                throw new UserNotFoundException();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }catch (UserNotFoundException e){
            return new GetUserProfileRes(request.getSenderId() , false , "User not found." , null , null , null , null , null , null , null , null);
        }
        return null;
    }

    public Response set_avatar(AvatarReq request)
    {
        ArrayList<User> allUsers = new ArrayList<>();

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)){
            while (fileInputStream.available() > 0){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(request.getSenderId())){
                    readUser.getProfile().setAvatar(request.getAvatar());
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
        return new Response(request.getSenderId(), true , "Avatar changed successfully.");
    }

    public Response set_header(HeaderReq request)
    {
        ArrayList<User> allUsers = new ArrayList<>();

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)){
            while (fileInputStream.available() > 0){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(request.getSenderId())){
                    readUser.getProfile().setHeader(request.getHeader());
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
        return new Response(request.getSenderId(), true , "Header changed successfully.");
    }

    public Response set_bio(BioReq request)
    {
        ArrayList<User> allUsers = new ArrayList<>();

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)){
            while (fileInputStream.available() > 0){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(request.getSenderId())){
                    readUser.getProfile().setBio(request.getBio());
                    readUser.getProfile().setLocation(request.getLocation());
                    readUser.getProfile().setWebsite(request.getWebsite());
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
        return new Response(request.getSenderId(), true , "Bio changed successfully.");
    }

    public Response follow(FollowReq request)
    {
        ArrayList<User> allUsers = new ArrayList<>();
        User actionUser = null;
        User targetUser = null;

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)){
            while (fileInputStream.available() > 0){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(request.getTargetId())){
                    targetUser = readUser;
                }
                else if(readUser.getId().equals(request.getSenderId())){
                    actionUser = readUser;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            return new Response(request.getSenderId(), false , e.getMessage());
        }

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)){
            while (fileInputStream.available() > 0){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(request.getSenderId())){
                    readUser.getFollowing().add(targetUser);
                }
                else if(readUser.getId().equals(request.getTargetId())){
                    readUser.getFollowers().add(actionUser);
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
        return new Response(request.getSenderId(), true , "User followed successfully.");
    }

    public Response unfollow(UnFollowReq request)
    {
        ArrayList<User> allUsers = new ArrayList<>();
        User targetUser = null;
        User actionUser = null;

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)){
            while (fileInputStream.available() > 0){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(request.getTargetId())){
                    targetUser = readUser;
                }
                else if(readUser.getId().equals(request.getSenderId())){
                    actionUser = readUser;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            return new Response(request.getSenderId(), false , e.getMessage());
        }

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)){
            while (fileInputStream.available() > 0){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(request.getSenderId())){
                    readUser.getFollowing().remove(targetUser);
                }
                else if(readUser.getId().equals(request.getTargetId())){
                    readUser.getFollowers().remove(actionUser);
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
        return new Response(request.getSenderId(), true , "User unfollowed successfully.");
    }

    public Response show_timeline(TimelineReq request)
    {
        ArrayList<Tweet> tweets = new ArrayList<>();

        ArrayList<User> following = new ArrayList<>();

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)){
            while (true){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(request.getSenderId())){
                    following = readUser.getFollowing();
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            return new Response(request.getSenderId(), false , e.getMessage());
        }


        for (User user : following) {
            tweets.addAll(user.getTweets());
        }

        for (int i = 0; i < tweets.size()-1; i++) {
            for (int j = 0; j < tweets.size(); j++) {
                if (tweets.get(i).getDate().after(tweets.get(j).getDate())){
                    Tweet temp = tweets.get(i);
                    tweets.add(i , tweets.get(j));
                    tweets.add(j , temp);
                }
            }
        }

        return new GetTimelineRes(request.getSenderId(), true , "time line of user sent" , tweets);
    }


    public Response block_user(BlockReq request)
    {
        ArrayList<User> allUsers = new ArrayList<>();
        User targetUser;

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)){
            while (true){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(request.getTargetId())){
                    targetUser = readUser;
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            return new Response(request.getSenderId(), false , e.getMessage());
        }

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)){
            while (fileInputStream.available() > 0){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(request.getSenderId())){
                    readUser.getBlackList().add(targetUser);
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
        return new Response(request.getSenderId(), true , "User blocked successfully.");
    }

    public Response unblock(UnblockReq request)
    {
        ArrayList<User> allUsers = new ArrayList<>();
        User targetUser;

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)){
            while (true){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(request.getTargetId())){
                    targetUser = readUser;
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            return new Response(request.getSenderId(), false , e.getMessage());
        }

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)){
            while (fileInputStream.available() > 0){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(request.getSenderId())){
                    readUser.getBlackList().remove(targetUser);
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
        return new Response(request.getSenderId(), true , "User unblocked successfully.");
    }

    public Response getFollowersList(FollowersListReq request){

        ArrayList<User> followerList = new ArrayList<>();

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)) {
            boolean flag = false;
            while (fileInputStream.available() > 0){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(request.getSenderId())){
                    followerList = readUser.getFollowers();
                    flag = true;
                    return new GetFollowersListRes(request.getSenderId() , true , "followers request list sent." , followerList);
                }
            }
            if (!flag){
                throw new ConfigNotFoundException("user not found.");
            }
        }
        catch (ConfigNotFoundException e) {
            return new GetFollowersListRes(request.getSenderId() , false , e.getMessage() , null);
        }catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException();
        }

        return null;
    }

    public Response getFollowingList(FollowingListReq request){
        ArrayList<User> followingList = new ArrayList<>();

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)) {
            boolean flag = false;
            while (fileInputStream.available() > 0){
                User readUser = (User) in.readObject();
                if (readUser.getId().equals(request.getSenderId())){
                    followingList = readUser.getFollowing();
                    flag = true;
                    return new GetFollowingListRes(request.getSenderId() , true , "following request list sent." , followingList);
                }
            }
            if (!flag){
                throw new ConfigNotFoundException("user not found.");
            }
        }
        catch (ConfigNotFoundException e) {
            return new GetFollowingListRes(request.getSenderId() , false , e.getMessage() , null);
        }catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException();
        }

        return null;
    }

    public Response search(SearchReq request) {
        ArrayList<User> resultUsers = new ArrayList<>();
        User senderUser = null;

        try(FileInputStream fileInputStream = new FileInputStream(config.getFILE_NAME());
            ObjectInputStream in = new ObjectInputStream(fileInputStream)) {
            boolean flag = false;
            while (fileInputStream.available() > 0){
                User readUser = (User) in.readObject();
                if (readUser.getName().equalsIgnoreCase(request.getSearchText()) || readUser.getLastname().equalsIgnoreCase(request.getSearchText()) || readUser.getId().equals(request.getSearchText())){
                    if (!readUser.getId().equals(request.getSenderId())){
                        resultUsers.add(readUser);
                    }
                }
                if(readUser.getId().equals(request.getSenderId())){
                    senderUser = readUser;
                    flag = true;
                }
            }
            if (!flag){
                throw new UserNotFoundException();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException();
        } catch (UserNotFoundException e) {
            return new GetSearchResultRes(request.getSenderId(), false , "sender user not found" , resultUsers , null);
        }
        return new GetSearchResultRes(request.getSenderId(), true , "result of search sent" , resultUsers , senderUser);
    }
}

package model;

import model.tweet.Tweet;

import java.io.Serializable;
import java.util.ArrayList;

public class Hashtag implements Serializable {
    private String name;
    private ArrayList<Tweet> tweets;
}

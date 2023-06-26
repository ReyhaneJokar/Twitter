package model.profile;

import javafx.scene.image.Image;

import java.io.Serializable;

public class Profile implements Serializable {
    private Image avatar, header;
    private String bio, location, website;

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public void setHeader(Image header) {
        this.header = header;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Image getAvatar() {
        return avatar;
    }

    public Image getHeader() {
        return header;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public String getWebsite() {
        return website;
    }
}

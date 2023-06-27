package model.profile;

import java.io.Serializable;

public class Profile implements Serializable {
    private byte[] avatar, header;
    private String bio, location, website;

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public void setHeader(byte[] header) {
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

    public byte[] getAvatar() {
        return avatar;
    }

    public byte[] getHeader() {
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

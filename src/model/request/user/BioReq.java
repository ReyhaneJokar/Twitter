package model.request.user;

public class BioReq extends UserRequest {
    private final String bio, website , location;

    public BioReq(String senderId, String bio, String website , String location) {
        super(senderId, UserRequestType.SET_BIO);
        this.bio = bio;
        this.website = website;
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public String getWebsite() {
        return website;
    }

    public String getLocation() {
        return location;
    }
}

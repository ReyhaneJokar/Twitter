package model.request.user;

public class BioReq extends UserRequest {
    private final String bio, website;

    public BioReq(String senderId, String bio, String website) {
        super(senderId, UserRequestType.SET_BIO);
        this.bio = bio;
        this.website = website;
    }

    public String getBio() {
        return bio;
    }

    public String getWebsite() {
        return website;
    }
}

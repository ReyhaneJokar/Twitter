package model.request.Authentication;


import connect.api.server.UserThread;

public class SignUpReq extends AuthenticationReq {
    private final String name, lastname, email, phone, country, birthdate;

    public SignUpReq(String senderId , UserThread userThread, String name, String lastname, String email, String phone, String password, String country, String birthdate) {
        super(senderId, AuthenticationReqType.SIGN_UP, password , userThread);
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCountry() {
        return country;
    }

    public String getBirthdate() {
        return birthdate;
    }
}

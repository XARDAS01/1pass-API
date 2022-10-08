package com.example.onepassAPI.Payloads.request;

public class AuthRegistrationRequest {
    private String firstName, lastName;
    private String phoneNumber, emailAddress;
    private String login, password;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}

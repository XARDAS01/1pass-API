package com.example.onepassAPI.Models;

import com.example.onepassAPI.Payloads.request.AuthRegistrationRequest;
import com.example.onepassAPI.Services.GeneratorService;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uid = GeneratorService.initUID();
    private String firstName, lastName;
    private String phoneNumber, emailAddress;
    private String login;

    public User() { }

    public User(AuthRegistrationRequest authRegistrationRequest) {
        this.firstName = authRegistrationRequest.getFirstName();
        this.lastName = authRegistrationRequest.getLastName();
        this.phoneNumber = authRegistrationRequest.getPhoneNumber();
        this.emailAddress = authRegistrationRequest.getEmailAddress();
        this.login = authRegistrationRequest.getLogin();
    }

    public String getUid() {
        return uid;
    }

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
}

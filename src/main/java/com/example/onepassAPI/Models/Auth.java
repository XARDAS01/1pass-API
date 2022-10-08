package com.example.onepassAPI.Models;

import com.example.onepassAPI.Payloads.request.AuthRegistrationRequest;

import javax.persistence.*;

@Entity
@Table(name = "auth")
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uid, login, password;

    public Auth() { }

    public Auth(String uid, AuthRegistrationRequest authRegistrationRequest) {
        this.uid = uid;
        this.login = authRegistrationRequest.getLogin();
        this.password = authRegistrationRequest.getPassword();
    }

    public String getUid() {
        return uid;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}

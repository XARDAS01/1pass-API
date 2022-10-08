package com.example.onepassAPI.Models;

import com.example.onepassAPI.Services.GeneratorService;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String token = GeneratorService.initToken();
    @NotNull
    private String uid;

    public Token() { }

    public Token(String uid) {
        this.uid = uid;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

package com.luczak.hsbc.socialapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

public class User {

    private final String username;

    @JsonIgnore
    private final Set<User> followers = new HashSet<>();
    @JsonIgnore
    private final Set<User> following = new HashSet<>();

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public Set<User> getFollowing() {
        return following;
    }
}

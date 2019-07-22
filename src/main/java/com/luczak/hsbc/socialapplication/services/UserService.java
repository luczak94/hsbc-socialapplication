package com.luczak.hsbc.socialapplication.services;

import com.luczak.hsbc.socialapplication.model.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    void addUser(User user);

    Set<User> getUsers();

    Optional<User> getUser(String username);

    Set<User> getFollowees(String username);

    Set<User> getFollowers(String username);

    void follow(String username, String followingUsermane);

    void unfollow(String username, String followingUsername);
}

package com.luczak.hsbc.socialapplication.services;

import com.luczak.hsbc.socialapplication.model.User;
import com.luczak.hsbc.socialapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.addUser(user);
    }

    @Override
    public Set<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public Optional<User> getUser(String username) {
        return userRepository.getUser(username);
    }

    @Override
    public Set<User> getFollowees(String username) {
        return getUser(username)
                .map(User::getFollowing)
                .orElse(Collections.emptySet());
    }

    @Override
    public Set<User> getFollowers(String username) {
        return getUser(username)
                .map(User::getFollowers)
                .orElse(Collections.emptySet());
    }

    @Override
    public void follow(String username, String followingUsername) {
        Optional<User> user = getUser(username);

        Optional<User> following = getUser(followingUsername);

        if (validateUsers(user, following)) {
            follow(user.get(), following.get());
        }
    }

    private void follow(User user, User following) {
        user.getFollowing().add(following);
        following.getFollowers().add(user);
    }

    @Override
    public void unfollow(String username, String followingUsername) {
        Optional<User> user = getUser(username);
        Optional<User> following = getUser(followingUsername);
        if (validateUsers(user, following)) {
            unfollow(user.get(), following.get());
        }
    }

    private boolean validateUsers(Optional<User> user, Optional<User> following) {
        return user.isPresent() && following.isPresent();
    }

    private void unfollow(User user, User following) {
        user.getFollowing().remove(following);
        following.getFollowers().remove(user);
    }
}

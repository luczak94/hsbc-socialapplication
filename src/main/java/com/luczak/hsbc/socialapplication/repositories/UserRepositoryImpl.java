package com.luczak.hsbc.socialapplication.repositories;

import com.luczak.hsbc.socialapplication.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class UserRepositoryImpl implements UserRepository {
    Set<User> users = new HashSet<>();

    @Override
    public Set<User> getUsers() {
        return users;
    }

    @Override
    public Optional<User> getUser(String username) {
        return users
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findAny();
    }

    @Override
    public boolean addUser(User user) {
        return users.add(user);
    }
}

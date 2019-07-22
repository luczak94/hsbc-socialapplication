package com.luczak.hsbc.socialapplication.repositories;

import com.luczak.hsbc.socialapplication.model.User;

import java.util.Optional;
import java.util.Set;

public interface UserRepository {

    Set<User> getUsers();

    Optional<User> getUser(String username);

    boolean addUser(User user);


}

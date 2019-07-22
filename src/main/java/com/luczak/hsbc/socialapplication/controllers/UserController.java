package com.luczak.hsbc.socialapplication.controllers;

import com.luczak.hsbc.socialapplication.model.User;
import com.luczak.hsbc.socialapplication.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@Api(description = "REST API for getting info about users")
public class UserController {
    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping(path = "/users/all", method = RequestMethod.GET)
    public ResponseEntity<Set<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @ResponseBody
    @GetMapping(path = "/users/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        Optional<User> user = userService.getUser(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @ResponseBody
    @GetMapping(path = "/{username}/following")
    public ResponseEntity<Set<User>> getFollowee(@PathVariable String username) {
        return ResponseEntity.ok(userService.getFollowees(username));
    }

    @PostMapping(path = "/{username}/following")
    public void follow(@PathVariable String username, @RequestBody String following) {
        userService.follow(username, following);
    }

    @ResponseBody
    @GetMapping(path = "/{username}/followers")
    public ResponseEntity<Set<User>> getFollowers(@PathVariable String username) {
        return ResponseEntity.ok(userService.getFollowers(username));
    }

    @DeleteMapping(path = "/{username}/following")
    public void unfollow(@PathVariable String username, @RequestBody String following) {
        userService.unfollow(username, following);
    }
}

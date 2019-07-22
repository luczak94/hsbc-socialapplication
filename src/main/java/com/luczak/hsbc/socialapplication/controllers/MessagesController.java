package com.luczak.hsbc.socialapplication.controllers;

import com.luczak.hsbc.socialapplication.model.Message;
import com.luczak.hsbc.socialapplication.services.MessageService;
import com.luczak.hsbc.socialapplication.services.MessagesNotFoundException;
import com.luczak.hsbc.socialapplication.services.exceptions.InvalidMessageException;
import com.luczak.hsbc.socialapplication.services.exceptions.UserNotFoundException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api( description = "REST API for getting messages")
public class MessagesController {
    @Autowired
    private MessageService messageService;

    @GetMapping(path = "/{username}/wall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Message>> getMessagesFromWall(@PathVariable String username) throws UserNotFoundException, MessagesNotFoundException {
        List<Message> messages = messageService.getUserMessages(username);
        return ResponseEntity.ok(messages);
    }

    @PostMapping(path = "/{username}/message")
    public ResponseEntity postTweet(@PathVariable String username, @RequestBody String message) {
        try {
            messageService.postMessage(username, message);
            return ResponseEntity.ok().build();

        } catch (InvalidMessageException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @ResponseBody
    @GetMapping(path = "/{username}/timeline")
    public ResponseEntity<List<Message>> getMessagesFromTimeline(@PathVariable String username) {
        return ResponseEntity.ok(messageService.getFolloweesMessages(username));
    }


}

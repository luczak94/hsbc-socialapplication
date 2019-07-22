package com.luczak.hsbc.socialapplication.services;

import com.luczak.hsbc.socialapplication.services.exceptions.InvalidMessageException;
import com.luczak.hsbc.socialapplication.services.exceptions.UserNotFoundException;
import com.luczak.hsbc.socialapplication.model.Message;

import java.util.List;

public interface MessageService {

    void postMessage(String username, String test) throws InvalidMessageException;

    List<Message> getUserMessages(String username) throws UserNotFoundException, MessagesNotFoundException;

    List<Message> getFolloweesMessages(String username);
}

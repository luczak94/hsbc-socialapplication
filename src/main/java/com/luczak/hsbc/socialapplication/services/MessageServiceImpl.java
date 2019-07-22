package com.luczak.hsbc.socialapplication.services;

import com.luczak.hsbc.socialapplication.services.exceptions.InvalidMessageException;
import com.luczak.hsbc.socialapplication.services.exceptions.UserNotFoundException;
import com.luczak.hsbc.socialapplication.model.Message;
import com.luczak.hsbc.socialapplication.model.User;
import com.luczak.hsbc.socialapplication.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {


    private static final int MAX_TEXT_LENGTH = 140;
    private static final Comparator<Message> REVERSED_MESSAGE_COMPARATOR = Comparator.comparing(Message::getCreatedAt).reversed();
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;


    @Override
    public void postMessage(String username, String text) throws InvalidMessageException {
        validateMessage(text);
        User user = getOrCreateUser(username);
        Message message = new Message(user, text);
        messageRepository.addMessage(message);
    }

    @Override
    public List<Message> getUserMessages(String username) throws UserNotFoundException, MessagesNotFoundException {
        Optional<User> user = userService.getUser(username);
        List<Message> messages = user.map(this::getMessagesByUser).orElseThrow(UserNotFoundException::new);
        validateMessages(messages);
        return messages;
    }


    @Override
    public List<Message> getFolloweesMessages(String username) {
        return userService.getFollowees(username)
                .stream()
                .map(this::getMessagesByUser)
                .flatMap(Collection::stream)
                .sorted(REVERSED_MESSAGE_COMPARATOR)
                .collect(Collectors.toList());
    }

    private void validateMessage(String text) throws InvalidMessageException {
        if (text.length() > MAX_TEXT_LENGTH) {
            throw new InvalidMessageException();
        }
    }

    private User getOrCreateUser(String username) {
        Optional<User> existingUser = userService.getUser(username);

        if (existingUser.isPresent()) {
            return existingUser.get();
        } else {
            User user = new User(username);
            userService.addUser(user);

            return user;
        }
    }

    private List<Message> getMessagesByUser(User user) {
        return messageRepository.getUserMessages(user)
                .stream()
                .sorted(REVERSED_MESSAGE_COMPARATOR)
                .collect(Collectors.toList());
    }

    private void validateMessages(List<Message> messages) throws MessagesNotFoundException {
        if (messages.isEmpty()) {
            throw new MessagesNotFoundException();
        }
    }
}

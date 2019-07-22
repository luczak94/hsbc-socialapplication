package com.luczak.hsbc.socialapplication.repositories;

import com.luczak.hsbc.socialapplication.model.Message;
import com.luczak.hsbc.socialapplication.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

    List<Message> messages = new ArrayList<>();

    @Override
    public void addMessage(Message message) {
        messages.add(message);
    }

    @Override
    public List<Message> getUserMessages(User user) {
        return messages
                .stream()
                .filter(message -> message.getUser().equals(user))
                .collect(Collectors.toList());
    }
}

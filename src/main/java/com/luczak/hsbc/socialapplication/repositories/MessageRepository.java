package com.luczak.hsbc.socialapplication.repositories;

import com.luczak.hsbc.socialapplication.model.Message;
import com.luczak.hsbc.socialapplication.model.User;

import java.util.List;

public interface MessageRepository {

    void addMessage(Message message);

    List<Message> getUserMessages(User user);

}

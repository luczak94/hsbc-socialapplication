package com.luczak.hsbc.socialapplication.model;

import java.util.Date;

public class Message {


    private final User user;
    private final String text;
    private final Date createdAt;

    public Message(User user, String text) {
        this.user = user;
        this.text = text;
        this.createdAt = new Date();
    }

    public User getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }


}

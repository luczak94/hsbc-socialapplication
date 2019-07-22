package com.luczak.hsbc.socialapplication.services.exceptions;

public class MessageNotFoundException extends Exception {

    private static final String MESSAGE_NOT_FOUND = "Message not found";

    public MessageNotFoundException() {
        super(MESSAGE_NOT_FOUND);
    }
}

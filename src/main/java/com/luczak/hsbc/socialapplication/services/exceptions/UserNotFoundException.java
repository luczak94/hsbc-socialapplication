package com.luczak.hsbc.socialapplication.services.exceptions;

public class UserNotFoundException extends Exception {

    private static final String MESSAGE = "User not found";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}

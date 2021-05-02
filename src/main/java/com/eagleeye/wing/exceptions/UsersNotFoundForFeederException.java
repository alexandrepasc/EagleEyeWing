package com.eagleeye.wing.exceptions;

import java.util.UUID;

public class UsersNotFoundForFeederException extends RuntimeException {

    public UsersNotFoundForFeederException(UUID id) {

        super("Users not found for feeder: " + id);
    }
}

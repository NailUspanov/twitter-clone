package com.uspanov.twitterclone.exceptions;


public class UserServiceException extends RuntimeException {

    private static final long serialVersionUID = 4442771269297625477L;

    public UserServiceException(String message) {
        super(message);
    }
}

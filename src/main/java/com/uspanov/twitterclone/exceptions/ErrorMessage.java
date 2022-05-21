package com.uspanov.twitterclone.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorMessage {
    private Date date = new Date();
    private String message;

    public ErrorMessage(String message) {
        this.message = message;
    }
}

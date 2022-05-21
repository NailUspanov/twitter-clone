package com.uspanov.twitterclone.model;

import lombok.Getter;

@Getter
public enum RequestOperationName {
    DELETE("DELETE");

    private final String operationName;

    RequestOperationName(String name) {
        this.operationName = name;
    }
}

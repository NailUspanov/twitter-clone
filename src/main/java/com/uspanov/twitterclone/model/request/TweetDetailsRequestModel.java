package com.uspanov.twitterclone.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TweetDetailsRequestModel {
    private String text;
    private String imgFileName;
}

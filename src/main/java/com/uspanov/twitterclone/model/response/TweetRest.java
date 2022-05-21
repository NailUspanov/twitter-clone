package com.uspanov.twitterclone.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TweetRest {
    private String tweetId;
    private String text;
    private String imgFileName;
}

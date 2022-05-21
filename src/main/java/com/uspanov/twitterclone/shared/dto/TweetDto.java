package com.uspanov.twitterclone.shared.dto;

import com.uspanov.twitterclone.entity.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class TweetDto implements Serializable {

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private static final long serialVersionUID = 6339343910769055321L;

    private long id;
    private String tweetId;
    private String text;
    private String imgFileName;
    private Date date;
    private UserDto user;
}

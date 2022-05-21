package com.uspanov.twitterclone.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "tweets")
@Getter
@Setter
public class TweetEntity implements Serializable {

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private static final long serialVersionUID = -984279093714990838L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String tweetId;

    @Column(nullable = false, length = 200)
    private String imgFileName;

    @Column(nullable = false, length = 280)
    private String text;

    @Column(nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}

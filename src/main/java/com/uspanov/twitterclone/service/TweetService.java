package com.uspanov.twitterclone.service;

import com.uspanov.twitterclone.shared.dto.TweetDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TweetService {

    TweetDto createTweet(String userId, TweetDto tweet);

    TweetDto getTweet(String tweetId);

    List<TweetDto> getTweetsByUserId(String userId, int page, int limit);

    TweetDto updateTweet(String tweetId, TweetDto tweetDto);

    void deleteTweet(String tweetId);
}

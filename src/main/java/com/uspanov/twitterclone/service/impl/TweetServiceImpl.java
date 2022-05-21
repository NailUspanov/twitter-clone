package com.uspanov.twitterclone.service.impl;

import com.uspanov.twitterclone.entity.TweetEntity;
import com.uspanov.twitterclone.entity.UserEntity;
import com.uspanov.twitterclone.exceptions.UserServiceException;
import com.uspanov.twitterclone.repository.TweetRepository;
import com.uspanov.twitterclone.repository.UserRepository;
import com.uspanov.twitterclone.service.TweetService;
import com.uspanov.twitterclone.shared.Utils;
import com.uspanov.twitterclone.shared.dto.TweetDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Override
    public TweetDto createTweet(String userId, TweetDto tweet) {

        TweetEntity tweetEntity = new TweetEntity();
        BeanUtils.copyProperties(tweet, tweetEntity);

        String publicTweetId = utils.generateUserId(30);
        UserEntity user = userRepository.findByUserId(userId);

        if (user == null) {
            throw new UsernameNotFoundException(userId);
        }

        tweetEntity.setTweetId(publicTweetId);
        tweetEntity.setDate(new Date());
        tweetEntity.setUser(user);

        TweetEntity storedTweetDetails = tweetRepository.save(tweetEntity);

        TweetDto returnValue = new TweetDto();

        modelMapper.map(storedTweetDetails, returnValue);

        return returnValue;
    }

    @Override
    public TweetDto getTweet(String tweetId) {
        TweetEntity tweetEntity = tweetRepository.findByTweetId(tweetId);

        if (tweetEntity == null) {
            throw new UsernameNotFoundException(tweetId);
        }

        TweetDto tweetDto = new TweetDto();
        BeanUtils.copyProperties(tweetEntity, tweetDto);
        return tweetDto;
    }

    @Override
    public List<TweetDto> getTweetsByUserId(String userId, int page, int limit) {
        List<TweetDto> returnValue = new ArrayList<>();

        Pageable pageableRequest = PageRequest.of(page, limit);
        UserEntity user = userRepository.findByUserId(userId);

        if (user == null) {
            throw new UsernameNotFoundException(userId);
        }

        Page<TweetEntity> tweetsPage = tweetRepository.findByUser(user, pageableRequest);
        List<TweetEntity> users = tweetsPage.getContent();

        for (TweetEntity tweetEntity : users) {
            TweetDto tweetDto = new TweetDto();
            modelMapper.map(tweetEntity, tweetDto);
            returnValue.add(tweetDto);
        }

        return returnValue;
    }

    @Override
    public TweetDto updateTweet(String tweetId, TweetDto tweetDto) {
        TweetDto returnValue = new TweetDto();
        TweetEntity tweetEntity = tweetRepository.findByTweetId(tweetId);

        if (tweetEntity == null) {
            throw new UserServiceException(tweetId);
        }

        tweetEntity.setText(tweetDto.getText());

        TweetEntity updatedEntity = tweetRepository.save(tweetEntity);

        modelMapper.map(updatedEntity, returnValue);

        return returnValue;
    }

    @Override
    public void deleteTweet(String tweetId) {
        TweetEntity tweetEntity = tweetRepository.findByTweetId(tweetId);

        if (tweetEntity == null) {
            throw new UserServiceException(tweetId);
        }

        tweetRepository.delete(tweetEntity);
    }
}

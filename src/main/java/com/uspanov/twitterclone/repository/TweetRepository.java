package com.uspanov.twitterclone.repository;

import com.uspanov.twitterclone.entity.TweetEntity;
import com.uspanov.twitterclone.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends PagingAndSortingRepository<TweetEntity, Long> {
    TweetEntity findByTweetId(String id);
    Page<TweetEntity> findByTweetId(String id, Pageable pageable);
    Page<TweetEntity> findByUser(UserEntity id, Pageable pageable);
}

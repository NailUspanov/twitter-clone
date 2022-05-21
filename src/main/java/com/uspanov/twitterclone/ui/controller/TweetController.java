package com.uspanov.twitterclone.ui.controller;

import com.uspanov.twitterclone.model.RequestOperationName;
import com.uspanov.twitterclone.model.request.TweetDetailsRequestModel;
import com.uspanov.twitterclone.model.response.OperationStatusModel;
import com.uspanov.twitterclone.model.response.TweetRest;
import com.uspanov.twitterclone.service.TweetService;
import com.uspanov.twitterclone.shared.dto.TweetDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tweets")
public class TweetController {

    @Autowired
    TweetService tweetService;

    @GetMapping(path = "/{id}")
    public List<TweetRest> getUserTweets(@PathVariable String id,
                                   @RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "25") int limit) {
        List<TweetRest> returnValue = new ArrayList<>();
        List<TweetDto> tweets = tweetService.getTweetsByUserId(id, page, limit);

        for (TweetDto tweetDto : tweets){
            TweetRest tweetRest = new TweetRest();
            BeanUtils.copyProperties(tweetDto, tweetRest);
            returnValue.add(tweetRest);
        }
        return returnValue;
    }

    @PostMapping(path = "/{id}")
    public TweetRest createTweet(@RequestParam String text,
                                 @RequestParam MultipartFile img,
                                 @PathVariable String id) {

        TweetDetailsRequestModel tweetDetails = new TweetDetailsRequestModel();
        tweetDetails.setText(text);
        tweetDetails.setImgFileName(img.getOriginalFilename());

        TweetRest returnValue = new TweetRest();

        TweetDto tweetDto = new TweetDto();
        BeanUtils.copyProperties(tweetDetails, tweetDto);

        TweetDto createdTweet = tweetService.createTweet(id, tweetDto);
        BeanUtils.copyProperties(createdTweet, returnValue);

        String path = "C:\\Users\\Наиль\\IdeaProjects\\twitter-clone\\src\\main\\resources\\img\\" + img.getOriginalFilename();

        File file = new File(path);
        try {
            Files.copy(img.getInputStream(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    @PutMapping("/{id}")
    public TweetRest updateTweet(@PathVariable String id, @RequestBody TweetDetailsRequestModel tweetDetails) {

        TweetRest returnValue = new TweetRest();

        TweetDto tweetDto = new TweetDto();
        BeanUtils.copyProperties(tweetDetails, tweetDto);

        TweetDto updatedTweet = tweetService.updateTweet(id, tweetDto);
        BeanUtils.copyProperties(updatedTweet, returnValue);

        return returnValue;
    }

    @DeleteMapping("/{id}")
    public OperationStatusModel deleteUser(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.getOperationName());

        tweetService.deleteTweet(id);

        returnValue.setOperationResult("SUCCESS");
        return returnValue;
    }
}

package com.ahmnouira.tweety.controller;

import java.security.Principal;

import com.ahmnouira.tweety.model.Tweet;
import com.ahmnouira.tweety.model.User;
import com.ahmnouira.tweety.service.TweetService;
import com.ahmnouira.tweety.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/tweets")
public class TweetController {

    private final TweetService tweetService;
    private final UserService userService;

    public TweetController(TweetService tweetService, UserService userService) {
        this.tweetService = tweetService;
        this.userService = userService;
    }

    @PostMapping
    /**
     * 
     * @param principal: has information about the logged in user
     * @param tweet:     tweet object
     * @return
     */
    public Mono<Tweet> save(Principal principal, @RequestBody Tweet tweet) {
        Mono<User> user = userService.getUserByUsername(principal.getName());
        return user.flatMap(u -> {
            tweet.setTweetUser(u);
            return tweetService.save(tweet);
        });
    }

    @GetMapping
    public Flux<Tweet> getAll(Principal principal) {
        return tweetService.getRelevantTweets(principal.getName());
    }

}

package com.ahmnouira.tweety.service;

import com.ahmnouira.tweety.model.Tweet;
import com.ahmnouira.tweety.repository.TweetRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
@Transactional(readOnly = true)
public class TweetService {

    private final TweetRepository tweetRepository;

    private final Scheduler dbScheduler;

    public TweetService(TweetRepository tweetRepository, Scheduler dScheduler) {
        this.tweetRepository = tweetRepository;
        this.dbScheduler = dScheduler;
    }

    @Transactional(rollbackFor = Exception.class)
    public Mono<Tweet> save(Tweet tweet) {
        return Mono.fromCallable(() -> tweetRepository.save(tweet)).publishOn(dbScheduler);

    }

    public Flux<Tweet> getTweets() {
        return Flux.fromIterable(tweetRepository.findAll()).publishOn(dbScheduler);
    }

    /**
     * will retrieve tweets intended for a user identified by a particular username
     * 
     * @param username
     * @return
     */
    public Flux<Tweet> getRelevantTweets(String username) {
        return Flux.fromIterable(tweetRepository.findByTweetUser_UsernameOrContentContains(username, "@" + username))
                .publishOn(dbScheduler);
    }

}

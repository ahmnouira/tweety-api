package com.ahmnouira.tweety.repository;

import java.util.List;

import com.ahmnouira.tweety.model.Tweet;

import org.springframework.data.repository.CrudRepository;

public interface TweetRepository extends CrudRepository<Tweet, Integer> {

    // Since tweets need to be listed for a particular logged in user on any tweets
    // that mention them(with the @ symbol in front of the name)
    List<Tweet> findByTweetUser_UsernameOrContentContains(String userString, String mention);
}

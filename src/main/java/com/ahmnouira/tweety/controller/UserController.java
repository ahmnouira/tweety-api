package com.ahmnouira.tweety.controller;

import java.security.Principal;

import com.ahmnouira.tweety.model.User;
import com.ahmnouira.tweety.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public Mono<User> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PutMapping("/{userId}/follow")
    public void followUser(Principal principal, @PathVariable String userId) {
        Mono<User> user = userService.getUserByUsername(principal.getName());
        user.subscribe(u -> {
            if (!u.getUserId().equalsIgnoreCase(userId)) {
                u.getFollowing().add(userId);
                userService.save(u);
            }
        });

    }

}

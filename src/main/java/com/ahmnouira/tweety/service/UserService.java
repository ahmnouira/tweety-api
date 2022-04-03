package com.ahmnouira.tweety.service;

import java.util.Arrays;

import com.ahmnouira.tweety.model.User;
import com.ahmnouira.tweety.repository.UserRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final Scheduler dbScheduler;

    public UserService(UserRepository userRepository, Scheduler dbScheduler) {
        this.userRepository = userRepository;
        this.dbScheduler = dbScheduler;
    }

    @Transactional(rollbackFor = Exception.class)
    public Mono<User> save(User user) {
        return Mono.fromCallable(() -> userRepository.save(user)).publishOn(dbScheduler);
    }

    public Mono<User> getUserByUsername(String username) {
        return Mono.fromCallable(() -> userRepository.findByUsername(username)).publishOn(dbScheduler);
    }

    public Mono<User> getByUserId(String userId) {
        return Mono.fromCallable(() -> userRepository.findById(userId).get()).publishOn(dbScheduler);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        throw new UsernameNotFoundException("To");

        /*
         * User user = userRepository.findByUsername(username);
         * 
         * if (user == null) {
         * throw new UsernameNotFoundException(username);
         * }
         */

        /*
         * return new
         * org.springframework.security.core.userdetails.User(user.getScreenName(),
         * user.getPassword(),
         * Arrays.asList(new SimpleGrantedAuthority(user.getRole().toString())));
         * 
         */
    }
}

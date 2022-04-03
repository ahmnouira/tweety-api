package com.ahmnouira.tweety.repository;

import com.ahmnouira.tweety.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String userString);
}

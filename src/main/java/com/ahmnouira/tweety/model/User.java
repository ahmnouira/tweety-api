package com.ahmnouira.tweety.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @NotNull
    private String userId;

    @JsonIgnore
    @NotNull
    private String password;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private Role role;

    private String bio;

    private String profileImage;

    @ElementCollection
    private Set<String> following;

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public Role getRole() {
        return this.role;
    }

    public String getUserId() {
        return this.userId;
    }

    public Set<String> getFollowing() {
        return this.following;
    }

}

package com.kuba.forum.services;

import com.kuba.forum.model.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> getUserByLogin(String login);

    Optional<User> getUserById(int userId);

    void addUser(User user);

    int getNumberOfPosts(int userId);
}

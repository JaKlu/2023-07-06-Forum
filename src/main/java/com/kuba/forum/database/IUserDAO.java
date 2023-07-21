package com.kuba.forum.database;

import com.kuba.forum.model.User;

import java.util.Optional;

public interface IUserDAO {
    Optional<User> getUserByLogin(final String login);

    Optional<User> getUserById(final int userId);

    void addUser(User user);

    int getNumberOfPosts(int userId);
}

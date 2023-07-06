package com.kuba.forum.database;

import com.kuba.forum.model.User;

public interface IUserDAO {
    User getUserByLogin(String login);

    User getUserById(int userId);

    void addUser(User user);
}

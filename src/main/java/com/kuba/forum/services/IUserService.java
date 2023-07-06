package com.kuba.forum.services;

import com.kuba.forum.model.User;

public interface IUserService {
    User getUserByLogin(String login);

    User getUserById(int userId);

    void addUser(User user);
}

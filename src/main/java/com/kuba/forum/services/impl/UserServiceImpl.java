package com.kuba.forum.services.impl;

import com.kuba.forum.database.IUserDAO;
import com.kuba.forum.model.User;
import com.kuba.forum.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserDAO userDAO;

    @Override
    public User getUserByLogin(String login) {
        return this.userDAO.getUserByLogin(login);
    }

    @Override
    public User getUserById(int userId) {
        return this.userDAO.getUserById(userId);
    }

    @Override
    public void addUser(User user) {
        this.userDAO.addUser(user);
    }
}

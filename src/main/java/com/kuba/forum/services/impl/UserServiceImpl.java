package com.kuba.forum.services.impl;

import com.kuba.forum.database.IUserDAO;
import com.kuba.forum.model.User;
import com.kuba.forum.model.view.FullUserDTO;
import com.kuba.forum.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserDAO userDAO;

    @Override
    public Optional<User> getUserByLogin(String login) {
        return this.userDAO.getUserByLogin(login);
    }

    @Override
    public Optional<User> getUserById(int userId) {
        return this.userDAO.getUserById(userId);
    }

    @Override
    public void addUser(User user) {
        this.userDAO.addUser(user);
    }

    @Override
    public int getNumberOfPosts(int userId) {
        return this.userDAO.getNumberOfPosts(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userDAO.getAllUsers();
    }

    @Override
    public List<FullUserDTO> getUsersContent() {
        return this.userDAO.getAllUsers().stream()
                .map(user -> new FullUserDTO(
                        user,
                        this.userDAO.getNumberOfPosts(user.getId())
                ))
                .toList();
    }
}

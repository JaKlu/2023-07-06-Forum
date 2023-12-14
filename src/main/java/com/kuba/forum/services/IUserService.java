package com.kuba.forum.services;

import com.kuba.forum.model.User;
import com.kuba.forum.model.view.FullUserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> getUserByLogin(String login);

    Optional<User> getUserById(int userId);

    int getNumberOfPosts(int userId);

    List<User> getAllUsers();

    List<FullUserDTO> getUsersContent();
}

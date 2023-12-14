package com.kuba.forum.services.impl;

import com.kuba.forum.database.PostRepository;
import com.kuba.forum.database.UserRepository;
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
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    @Override
    public Optional<User> getUserByLogin(String login) {
        return this.userRepository.findByLogin(login);
    }

    @Override
    public Optional<User> getUserById(int userId) {
        return this.userRepository.findById(userId);
    }

    @Override
    public int getNumberOfPosts(int userId) {
        return this.postRepository.countByAuthorId(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public List<FullUserDTO> getUsersContent() {
        return this.userRepository.findAll().stream()
                .map(user -> new FullUserDTO(
                        user,
                        this.postRepository.countByAuthorId(user.getId())
                ))
                .toList();
    }
}

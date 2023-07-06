package com.kuba.forum.services;

import com.kuba.forum.exceptions.LoginAlreadyExistException;
import com.kuba.forum.model.User;

public interface IAuthenticationService {
    void authenticate(String login, String password);

    void logout();

    void register(User user) throws LoginAlreadyExistException;

}

package com.kuba.forum.services;

import com.kuba.forum.exceptions.LoginAlreadyExistException;
import com.kuba.forum.model.User;
import jakarta.servlet.http.HttpServletRequest;

public interface IAuthenticationService {
    void authenticate(String login, String password);

    void logout(HttpServletRequest request);

    void register(User user) throws LoginAlreadyExistException;
}

package com.kuba.forum.services.impl;

import com.kuba.forum.database.IUserDAO;
import com.kuba.forum.exceptions.LoginAlreadyExistException;
import com.kuba.forum.model.User;
import com.kuba.forum.services.IAuthenticationService;
import com.kuba.forum.session.SessionData;
import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {
    @Autowired
    IUserDAO userDAO;
    @Resource
    SessionData sessionData;

    @Override
    public void authenticate(String login, String password) {
        User user = this.userDAO.getUserByLogin(login);
        if (user != null && user.getPassword().equals(DigestUtils.md5Hex(password))) {
            user.setPassword(null);
            this.sessionData.setUser(user);
        }
    }

    @Override
    public void logout() {
        this.sessionData.setUser(null);
    }

    @Override
    public void register(User user) throws LoginAlreadyExistException {
        if (this.userDAO.getUserByLogin(user.getLogin()) != null) {
            throw new LoginAlreadyExistException();
        }
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        this.userDAO.addUser(user);

    }
}

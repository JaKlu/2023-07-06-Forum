package com.kuba.forum.services.impl;

import com.kuba.forum.database.IUserDAO;
import com.kuba.forum.exceptions.LoginAlreadyExistException;
import com.kuba.forum.model.User;
import com.kuba.forum.services.IAuthenticationService;
import com.kuba.forum.session.SessionData;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {
    @Autowired
    IUserDAO userDAO;
    @Resource
    SessionData sessionData;

    @Override
    public void authenticate(String login, String password) {
        Optional<User> user = this.userDAO.getUserByLogin(login);
        if (user.isPresent() && user.get().getPassword().equals(DigestUtils.md5Hex(password))) {
            //TODO powtórne logowanie - kopiowanie usera
            user.get().setPassword(null);
            this.sessionData.setUser(user.get());
        }
    }

    @Override
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @Override
    public void register(User user) throws LoginAlreadyExistException {
        if (this.userDAO.getUserByLogin(user.getLogin()).isPresent()) {
            throw new LoginAlreadyExistException();
        }
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        this.userDAO.addUser(user);
    }
}

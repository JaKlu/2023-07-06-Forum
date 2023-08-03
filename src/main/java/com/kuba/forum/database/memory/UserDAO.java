package com.kuba.forum.database.memory;

import com.kuba.forum.database.IPostDAO;
import com.kuba.forum.database.IUserDAO;
import com.kuba.forum.database.sequences.IUserIdSequence;
import com.kuba.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO implements IUserDAO {
    IUserIdSequence userIdSequence;
    @Autowired
    IPostDAO postDAO;
    private final List<User> users = new ArrayList<>();

    public UserDAO(@Autowired IUserIdSequence userIdSequence) {
        // password = login
        this.users.add(new User(userIdSequence.getId(),
                "admin", "21232f297a57a5a743894a0e4a801fc3", "admin@filmovie.com",
                LocalDate.of(1995, 7, 18), User.Gender.MALE,
                LocalDateTime.of(LocalDate.of(2023, 7, 1),
                        LocalTime.of(12, 0, 0)),
                "Rzeszów", User.Function.ADMIN));
        this.users.add(new User(userIdSequence.getId(),
                "kuba", "fccbce33643556ee698db7d599853a1f", "kuba@wp.pl",
                LocalDate.of(1994, 3, 12), User.Gender.MALE,
                LocalDateTime.of(LocalDate.of(2023, 7, 2),
                        LocalTime.of(13, 15, 0)),
                "Kraków", User.Function.USER));
        this.users.add(new User(userIdSequence.getId(),
                "wojtek", "0d333f240498cfd51eb8bd1d74ee0f6e", "wojtek@wp.pl",
                LocalDate.of(1997, 12, 26), User.Gender.MALE,
                LocalDateTime.of(LocalDate.of(2023, 7, 3),
                        LocalTime.of(10, 12, 0)),
                "Warszawa", User.Function.ADMIN));
        this.users.add(new User(userIdSequence.getId(),
                "ania", "5f59ac736640f43e61c6070284bf1c06", "ania@wp.pl",
                LocalDate.of(2001, 2, 13), User.Gender.FEMALE,
                LocalDateTime.of(LocalDate.of(2023, 7, 4),
                        LocalTime.of(8, 7, 0)),
                "Gdańsk", User.Function.USER));

        this.userIdSequence = userIdSequence;
    }

    @Override
    public Optional<User> getUserByLogin(final String login) {
        return this.users.stream()
                .filter(user -> user.getLogin().equals(login))
                .map(User::copyOf)
                .findFirst();
    }

    @Override
    public Optional<User> getUserById(final int userId) {
        return this.users.stream()
                .filter(user -> user.getId() == userId)
                .map(User::copyOf)
                .findFirst();
    }

    @Override
    public void addUser(User user) {
        user.setId(this.userIdSequence.getId());
        user.setJoinDate(LocalDateTime.now());
        user.setFunction(User.Function.USER);
        this.users.add(user);
    }


    @Override
    public int getNumberOfPosts(int userId) {
        return this.postDAO.getAllUserPosts(userId).size();
    }

    @Override
    public List<User> getAllUsers() {
        throw new RuntimeException();
    }
}

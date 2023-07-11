package com.kuba.forum.database.memory;

import com.kuba.forum.database.IPostDAO;
import com.kuba.forum.database.IUserDAO;
import com.kuba.forum.database.sequences.IUserIdSequence;
import com.kuba.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO implements IUserDAO {
    IUserIdSequence userIdSequence;
    @Autowired
    IPostDAO postDAO;
    private final List<User> users = new ArrayList<>();

    public UserDAO(@Autowired IUserIdSequence userIdSequence) {
        this.users.add(new User(userIdSequence.getId(),
                "admin", "21232f297a57a5a743894a0e4a801fc3", "admin@filmovie.com",
                LocalDate.of(1995, 7, 18), User.Gender.MALE,
                ZonedDateTime.of(LocalDate.of(2023, 7, 1),
                        LocalTime.of(12, 0, 0), ZoneId.of("Europe/Warsaw")),
                "Rzeszów", User.Function.ADMIN));
        this.users.add(new User(userIdSequence.getId(),
                "kuba", "fccbce33643556ee698db7d599853a1f", "kuba@wp.pl",
                LocalDate.of(1994, 3, 12), User.Gender.MALE,
                ZonedDateTime.of(LocalDate.of(2023, 7, 2),
                        LocalTime.of(13, 15, 0), ZoneId.of("Europe/Warsaw")),
                "Kraków", User.Function.USER));

        this.userIdSequence = userIdSequence;
    }

    @Override
    public User getUserByLogin(String login) {
        for (User user : this.users) {
            if (user.getLogin().equals(login)) {
                return User.copyOf(user);
            }
        }
        return null;
    }

    @Override
    public User getUserById(int userId) {
        for (User user : this.users) {
            if (user.getId() == userId) {
                return User.copyOf(user);
            }
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        user.setId(this.userIdSequence.getId());
        user.setJoinDate(ZonedDateTime.now());
        user.setFunction(User.Function.USER);
        this.users.add(user);
    }


    @Override
    public int getNumberOfPosts(int userId) {
        return this.postDAO.getAllUserPosts(userId).size();
    }
}

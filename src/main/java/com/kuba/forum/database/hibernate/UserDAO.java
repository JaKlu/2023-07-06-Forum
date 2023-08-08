package com.kuba.forum.database.hibernate;

import com.kuba.forum.database.IUserDAO;
import com.kuba.forum.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/* Login = Hasło */

@Repository
public class UserDAO implements IUserDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Optional<User> getUserByLogin(String login) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM com.kuba.forum.model.User WHERE login = :login", User.class);
        query.setParameter("login", login);
        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<User> getUserById(int userId) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM com.kuba.forum.model.User WHERE id = :userId", User.class);
        query.setParameter("userId", userId);
        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            user.setFunction(User.Function.USER);
            user.setJoinDate(LocalDateTime.now());
            session.merge(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public int getNumberOfPosts(int userId) {
        Session session = sessionFactory.openSession();

        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM com.kuba.forum.model.Post WHERE authorId = :userId", Long.class);
        query.setParameter("userId", userId);
        long result = query.uniqueResult();
        session.close();

        return (int) result;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM com.kuba.forum.model.User", User.class);
        List<User> resultList = query.getResultList();
        session.close();
        return resultList;
    }
}

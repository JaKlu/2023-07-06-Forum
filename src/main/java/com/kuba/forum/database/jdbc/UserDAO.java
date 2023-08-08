package com.kuba.forum.database.jdbc;

import com.kuba.forum.database.IUserDAO;
import com.kuba.forum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO implements IUserDAO {
    /*Login = Hasło*/
    @Autowired
    Connection connection;

    @Override
    public Optional<User> getUserByLogin(String login) {
        try {
            String sql = "SELECT * FROM tuser WHERE login = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getDate("birthday").toLocalDate(),
                        User.Gender.valueOf(rs.getString("gender")),
                        rs.getTimestamp("join_date").toLocalDateTime(),
                        rs.getString("place"),
                        User.Function.valueOf(rs.getString("function"))
                ));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> getUserById(int userId) {
        try {
            String sql = "SELECT * FROM tuser WHERE id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getDate("birthday").toLocalDate(),
                        User.Gender.valueOf(rs.getString("gender")),
                        rs.getTimestamp("join_date").toLocalDateTime(),
                        rs.getString("place"),
                        User.Function.valueOf(rs.getString("function"))
                ));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUser(User user) {
        try {
            String sql = "INSERT INTO tuser (login, password, email, birthday, gender, join_date, place, function) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setDate(4, Date.valueOf(user.getBirthday()));
            ps.setString(5, user.getGender().name());
            ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(7, user.getPlace());
            ps.setString(8, User.Function.USER.name());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getNumberOfPosts(int userId) {
        try {
            String sql = "SELECT COUNT(*) FROM tpost WHERE author_id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tuser";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getDate("birthday").toLocalDate(),
                        User.Gender.valueOf(rs.getString("gender")),
                        rs.getTimestamp("join_date").toLocalDateTime(),
                        rs.getString("place"),
                        User.Function.valueOf(rs.getString("function")))
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}

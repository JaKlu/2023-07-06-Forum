package com.kuba.forum.database.jdbc;

import com.kuba.forum.database.IThreadDAO;
import com.kuba.forum.database.ITopicDAO;
import com.kuba.forum.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TopicDAO implements ITopicDAO {
    @Autowired
    Connection connection;
    @Autowired
    IThreadDAO threadDAO;

    @Override
    public Topic addTopic(Topic topic) {
        try {
            String sql = "INSERT INTO ttopic (name, description) " +
                    "VALUES (?, ?)";
            PreparedStatement ps = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, topic.getName());
            ps.setString(2, topic.getDescription());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            topic.setId(rs.getInt(1));
            return topic;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Topic> getAllTopics() {
        List<Topic> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ttopic";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new Topic(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Optional<Topic> findTopicById(int topicId) {
        try {
            String sql = "SELECT * FROM ttopic WHERE id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, topicId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new Topic(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                ));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getNumberOfThreadsInTopic(int topicId) {
        try {
            String sql = "SELECT COUNT(*) FROM tthread WHERE topic_id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, topicId);
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
    public void deleteTopic(int topicId) {
        try {
            String sql = "DELETE FROM ttopic WHERE id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, topicId);
            ps.executeUpdate();
            this.threadDAO.getThreadsInTopic(topicId)
                    .forEach(thread -> this.threadDAO.deleteThread(thread.getId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editTopic(Topic topic) {
        try {
            String sql = "UPDATE ttopic " +
                    "SET name = ?, description = ? " +
                    "WHERE id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, topic.getName());
            ps.setString(2, topic.getDescription());
            ps.setInt(3, topic.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

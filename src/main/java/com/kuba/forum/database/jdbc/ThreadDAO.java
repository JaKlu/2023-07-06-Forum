package com.kuba.forum.database.jdbc;

import com.kuba.forum.database.IPostDAO;
import com.kuba.forum.database.IThreadDAO;
import com.kuba.forum.model.Thread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ThreadDAO implements IThreadDAO {
    @Autowired
    Connection connection;
    @Autowired
    IPostDAO postDAO;

    @Override
    public Thread addThread(Thread thread) {
        try {
            String sql = "INSERT INTO tthread (author_id, topic_id, subject, creation_time) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement ps = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, thread.getAuthorId());
            ps.setInt(2, thread.getTopicId());
            ps.setString(3, thread.getSubject());
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            thread.setId(rs.getInt(1));
            return thread;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Thread> getThreadsInTopic(int topicId) {
        List<Thread> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tthread WHERE topic_id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, topicId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new Thread(
                        rs.getInt("id"),
                        rs.getInt("author_id"),
                        rs.getInt("topic_id"),
                        rs.getString("subject"),
                        rs.getTimestamp("creation_time").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Optional<Thread> findThreadById(int threadId) {
        try {
            String sql = "SELECT * FROM tthread WHERE id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, threadId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new Thread(
                        rs.getInt("id"),
                        rs.getInt("author_id"),
                        rs.getInt("topic_id"),
                        rs.getString("subject"),
                        rs.getTimestamp("creation_time").toLocalDateTime()
                ));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editThread(Thread thread) {
        try {
            String sql = "UPDATE tthread " +
                    "SET author_id = ?, topic_id = ?, subject = ?, creation_time = ? " +
                    "WHERE id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, thread.getAuthorId());
            ps.setInt(2, thread.getTopicId());
            ps.setString(3, thread.getSubject());
            ps.setTimestamp(4, Timestamp.valueOf(thread.getCreationTime()));
            ps.setInt(5, thread.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getNumberOfRepliesInThread(int threadId) {
        try {
            String sql = "SELECT COUNT(*) FROM tpost WHERE thread_id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, threadId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { //TODO if? sprawdzić dla 0 postów
                return Math.max(rs.getInt(1) - 1, 0);
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteThread(int threadId) {
        try {
            String sql = "DELETE FROM tthread WHERE id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, threadId);
            ps.executeUpdate();
            this.postDAO.deleteAllPostsFromThread(threadId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

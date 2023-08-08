package com.kuba.forum.database.jdbc;

import com.kuba.forum.database.IPostDAO;
import com.kuba.forum.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostDAO implements IPostDAO {
    @Autowired
    Connection connection;

    @Override
    public Post addPost(Post post) {
        try {
            String sql = "INSERT INTO tpost (thread_id, author_id, creation_time, contents) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement ps = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, post.getThreadId());
            ps.setInt(2, post.getAuthorId());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(4, post.getContents());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            post.setId(rs.getInt(1));
            return post;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tpost";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            createListOfPosts(rs, result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    @Override
    public List<Post> getQueriedPosts(String query) {
        List<Post> result = new ArrayList<>();

        try {
            String sql = "SELECT * FROM tpost WHERE contents LIKE ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, "%" + query + "%");
            ResultSet rs = ps.executeQuery();
            createListOfPosts(rs, result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Post> getPostsFromThread(int threadId) {
        List<Post> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tpost WHERE thread_id = ?"; //TODO ASC? Order by
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, threadId);
            ResultSet rs = ps.executeQuery();
            createListOfPosts(rs, result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Post> getAllUserPosts(int userId) {
        List<Post> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tpost WHERE author_id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            createListOfPosts(rs, result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Optional<Post> getPostById(int id) {
        try {
            String sql = "SELECT * FROM tpost WHERE id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new Post(
                        rs.getInt("id"),
                        rs.getInt("thread_id"),
                        rs.getInt("author_id"),
                        rs.getTimestamp("creation_time").toLocalDateTime(),
                        rs.getString("contents")
                ));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editPost(Post post) {
        try {
            String sql = "UPDATE tpost SET thread_id=?, author_id=?, creation_time=?, contents=? WHERE id=?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, post.getThreadId());
            ps.setInt(2, post.getAuthorId());
            ps.setTimestamp(3, Timestamp.valueOf(post.getCreationTime()));
            ps.setString(4, post.getContents());
            ps.setInt(5, post.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAllPostsFromThread(int threadId) {
        try {
            String sql = "DELETE FROM tpost WHERE thread_id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, threadId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deletePost(int postId) {
        try {
            String sql = "DELETE FROM tpost WHERE id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, postId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createListOfPosts(ResultSet rs, List<Post> result) throws SQLException {
        while (rs.next()) {
            result.add(new Post(
                    rs.getInt("id"),
                    rs.getInt("thread_id"),
                    rs.getInt("author_id"),
                    rs.getTimestamp("creation_time").toLocalDateTime(),
                    rs.getString("contents")
            ));
        }
    }
}

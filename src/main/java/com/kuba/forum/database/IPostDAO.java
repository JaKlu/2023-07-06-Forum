package com.kuba.forum.database;

import com.kuba.forum.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IPostDAO {
    List<Post> getAllPosts();

    List<Post> getQueriedPosts(String query);

    List<Post> getPostsFromThread(int threadId);

    List<Post> getAllUserPosts(int userId);

    Post addPost(Post post);

    Optional<Post> getPostById(int id);

    void editPost(Post post);

    boolean deletePost(int postId);

    void deleteAllPostsFromThread(int threadId);

}

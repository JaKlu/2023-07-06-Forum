package com.kuba.forum.database;

import com.kuba.forum.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IPostDAO {
    Post addPost(Post post);

    List<Post> getAllPosts();

    List<Post> getQueriedPosts(String query);

    List<Post> getPostsFromThread(int threadId);

    List<Post> getAllUserPosts(int userId);

    Optional<Post> getPostById(int id);

    void editPost(Post post);

    void deleteAllPostsFromThread(int threadId);

    boolean deletePost(int postId);

}

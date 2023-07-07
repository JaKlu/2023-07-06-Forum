package com.kuba.forum.database;

import com.kuba.forum.model.Post;

import java.util.List;
import java.util.Set;

public interface IPostDAO {
    List<Post> getAllPosts();

    List<Post> getPostsFromThread(int threadId);

    Post addPost(Post post);

    Post getPostById(int id);

    void editPost(Post post);

    void deletePost(int postId);


}

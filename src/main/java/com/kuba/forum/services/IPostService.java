package com.kuba.forum.services;

import com.kuba.forum.model.Post;

import java.util.List;

public interface IPostService {
    Post getPostById(int postId);

    Post addPost(Post post);

    List<Post> getPostsFromThread(int threadId);

    void deletePost(int id);

    void editPost(Post post);
}

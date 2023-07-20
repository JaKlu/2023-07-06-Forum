package com.kuba.forum.services;

import com.kuba.forum.model.Post;
import com.kuba.forum.model.view.FullPostDTO;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    Optional<Post> getPostById(int postId);

    List<Post> getQueriedPosts(String query);

    Post addPost(Post post);

    List<Post> getPostsFromThread(int threadId);

    List<FullPostDTO> getThreadContent(int threadId);

    void deletePost(int id);

    void editPost(Post post);

    void deleteAllPostsFromThread(int threadId);
}

package com.kuba.forum.services.impl;

import com.kuba.forum.database.IPostDAO;
import com.kuba.forum.model.Post;
import com.kuba.forum.services.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    IPostDAO postDAO;

    @Override
    public Post getPostById(int postId) {
        return this.postDAO.getPostById(postId);
    }

    @Override
    public List<Post> getQueriedPosts(String query) {
        return this.postDAO.getQueriedPosts(query);
    }

    @Override
    public Post addPost(Post post) {
        return this.postDAO.addPost(post);
    }

    @Override
    public List<Post> getPostsFromThread(int threadId) {
        return this.postDAO.getPostsFromThread(threadId);
    }

    @Override
    public void deletePost(int postId) {
        this.postDAO.deletePost(postId);
    }

    @Override
    public void editPost(Post post) {
        this.postDAO.editPost(post);
    }
}

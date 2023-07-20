package com.kuba.forum.services.impl;

import com.kuba.forum.database.IPostDAO;
import com.kuba.forum.database.IThreadDAO;
import com.kuba.forum.database.IUserDAO;
import com.kuba.forum.model.Post;
import com.kuba.forum.model.view.FullPostDTO;
import com.kuba.forum.services.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    IPostDAO postDAO;
    @Autowired
    IThreadDAO threadDAO;
    @Autowired
    IUserDAO userDAO;

    @Override
    public Optional<Post> getPostById(int postId) {
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
    public List<FullPostDTO> getThreadContent(final int threadId) {
/*
        List<FullPostDTO> getThreadContent = new ArrayList<>();
        for (Post post : this.postDAO.getPostsFromThread(threadId)) {
            if (post.getThreadId() == threadId) {
                getThreadContent.add(new FullPostDTO(
                        post, this.threadDAO.findThreadById(threadId).get(), this.userDAO.getUserById(post.getAuthorId()),
                        this.userDAO.getNumberOfPosts(post.getAuthorId())));
            }
        }
        return getThreadContent;
*/

        return this.postDAO.getAllPosts().stream()
                .filter(post -> post.getThreadId() == threadId)
                .map(post -> new FullPostDTO(
                        post, this.threadDAO.findThreadById(threadId).get(), this.userDAO.getUserById(post.getAuthorId()),
                        this.userDAO.getNumberOfPosts(post.getAuthorId())))
                .toList();

    }

    @Override
    public void deletePost(int postId) {
        this.postDAO.deletePost(postId);
    }

    @Override
    public void deleteAllPostsFromThread(int threadId) {
        this.postDAO.deleteAllPostsFromThread(threadId);
    }

    @Override
    public void editPost(Post post) {
        this.postDAO.editPost(post);
    }
}

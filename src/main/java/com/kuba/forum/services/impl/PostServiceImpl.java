package com.kuba.forum.services.impl;

import com.kuba.forum.database.PostRepository;
import com.kuba.forum.database.ThreadRepository;
import com.kuba.forum.database.UserRepository;
import com.kuba.forum.model.Post;
import com.kuba.forum.model.view.FullPostDTO;
import com.kuba.forum.services.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    ThreadRepository threadRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<Post> getPostById(int postId) {
        return this.postRepository.findById(postId);
    }

    @Override
    public List<Post> getQueriedPosts(String query) {
        return this.postRepository.findByContentsContainingIgnoreCase(query);
    }

    @Override
    public List<FullPostDTO> getPostsFromQuery(String pattern) {
        return this.postRepository.findByContentsContainingIgnoreCase(pattern).stream()
                .map(post -> new FullPostDTO(
                        post,
                        this.threadRepository.findById(post.getThreadId()).get(),
                        this.userRepository.findById(post.getAuthorId()).get(),
                        this.postRepository.countByAuthorId(post.getAuthorId())))
                .toList();
    }

    @Override
    public Post addPost(Post post) {
        post.setCreationTime(LocalDateTime.now());
        return this.postRepository.save(post);
    }

    @Override
    public List<Post> getPostsFromThread(int threadId) {
        return this.postRepository.findByThreadId(threadId);
    }

    @Override
    public List<FullPostDTO> getThreadContent(final int threadId) {
        return this.postRepository.findByThreadId(threadId).stream()
                .map(post -> new FullPostDTO(
                        post,
                        this.threadRepository.findById(threadId).get(),
                        this.userRepository.findById(post.getAuthorId()).get(),
                        this.postRepository.countByAuthorId(post.getAuthorId())))
                .sorted(Comparator.comparing(fullPostDTO -> fullPostDTO.getPost().getCreationTime()))
                .collect(Collectors.toList());
    }

    @Override
    public Long deletePost(int postId) {
        return this.postRepository.deleteById(postId);
    }

    @Override
    public void deleteAllPostsFromThread(int threadId) {
        this.postRepository.deleteByThreadId(threadId);
    }

    @Override
    public void editPost(Post post) {
        Optional<Post> postToUpdateBox = this.postRepository.findById(post.getId());
        if (postToUpdateBox.isPresent()) {
            Post updatedPost = postToUpdateBox.get();
            updatedPost.setContents(post.getContents());
            this.postRepository.save(updatedPost);
        }
    }
}

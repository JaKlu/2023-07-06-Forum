package com.kuba.forum.database;

import com.kuba.forum.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findById(int id);

    List<Post> findByThreadId(int threadId);

    List<Post> findByContentsContainingIgnoreCase(String pattern);

    Long deleteById(int postId);

    Long deleteByThreadId(int threadId);

    Integer countByThreadId(int threadId);

    Integer countByAuthorId(int authorId);
}

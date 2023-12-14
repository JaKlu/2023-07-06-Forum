package com.kuba.forum.database;

import com.kuba.forum.model.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Integer> {
    Optional<Thread> findById(int threadId);

    List<Thread> findByTopicId(int topicId);

    void deleteById(int threadId);

    Integer countByTopicId(int topicId);
}

package com.kuba.forum.database;

import com.kuba.forum.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {
    Optional<Topic> findById(final int topicId);

    void deleteById(final int topicId);
}

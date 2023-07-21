package com.kuba.forum.database;

import com.kuba.forum.model.Topic;

import java.util.List;
import java.util.Optional;

public interface ITopicDAO {
    List<Topic> getAllTopics();

    Optional<Topic> findTopicById(final int topicId);

    Topic addTopic(Topic topic);

    void deleteTopic(final int topicId);

    int getNumberOfThreadsInTopic(int topicId);
}

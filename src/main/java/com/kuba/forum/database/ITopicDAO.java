package com.kuba.forum.database;

import com.kuba.forum.model.Topic;

import java.util.List;
import java.util.Optional;

public interface ITopicDAO {
    Topic addTopic(Topic topic);

    List<Topic> getAllTopics();

    Optional<Topic> findTopicById(final int topicId);

    int getNumberOfThreadsInTopic(int topicId);

    void deleteTopic(final int topicId);

    void editTopic(Topic topic);
}

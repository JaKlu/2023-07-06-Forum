package com.kuba.forum.database;

import com.kuba.forum.model.Topic;

import java.util.List;

public interface ITopicDAO {
    List<Topic> getAllTopics();

    Topic findTopicById(int topicId);

    Topic addTopic(Topic topic);

    void deleteTopic(int topicId);
}

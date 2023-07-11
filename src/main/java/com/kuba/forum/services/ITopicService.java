package com.kuba.forum.services;

import com.kuba.forum.model.Thread;
import com.kuba.forum.model.Topic;

import java.util.List;

public interface ITopicService {

    List<Topic> getAllTopics();

    Topic findTopicById(int topicId);

    Topic addTopic(Topic topic);

    void deleteTopic(int topicId);
}

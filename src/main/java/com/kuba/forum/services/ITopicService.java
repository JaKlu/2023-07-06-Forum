package com.kuba.forum.services;

import com.kuba.forum.model.Topic;
import com.kuba.forum.model.view.FullTopicDTO;

import java.util.List;
import java.util.Optional;

public interface ITopicService {
    List<Topic> getAllTopics();

    List<FullTopicDTO> getForumContent();

    Optional<Topic> findTopicById(int topicId);

    Topic addTopic(Topic topic);

    void deleteTopic(int topicId);
}

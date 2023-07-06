package com.kuba.forum.services.impl;

import com.kuba.forum.database.ITopicDAO;
import com.kuba.forum.model.Topic;
import com.kuba.forum.services.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements ITopicService {
    @Autowired
    ITopicDAO topicDAO;

    public List<Topic> getAllTopics() {
        return this.topicDAO.getAllTopics();
    }

    @Override
    public Topic findTopicById(int topicId) {
        return this.topicDAO.findTopicById(topicId);
    }

    public void addTopic(Topic topic) {
        this.topicDAO.addTopic(topic);
    }
}

package com.kuba.forum.services.impl;

import com.kuba.forum.database.ITopicDAO;
import com.kuba.forum.model.Topic;
import com.kuba.forum.model.view.FullTopicDTO;
import com.kuba.forum.services.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements ITopicService {
    @Autowired
    ITopicDAO topicDAO;

    @Override
    public List<Topic> getAllTopics() {
        return this.topicDAO.getAllTopics();
    }

    @Override
    public List<FullTopicDTO> getForumContent() {
        return this.topicDAO.getAllTopics().stream()
                .map(topic -> new FullTopicDTO(
                        topic,
                        this.topicDAO.getNumberOfThreadsInTopic(topic.getId())
                ))
                .toList();
    }

    @Override
    public Optional<Topic> findTopicById(int topicId) {
        return this.topicDAO.findTopicById(topicId);
    }

    @Override
    public Topic addTopic(Topic topic) {
        return this.topicDAO.addTopic(topic);
    }

    @Override
    public void deleteTopic(int topicId) {
        this.topicDAO.deleteTopic(topicId);
    }
}

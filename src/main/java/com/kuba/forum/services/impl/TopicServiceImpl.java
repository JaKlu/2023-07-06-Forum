package com.kuba.forum.services.impl;

import com.kuba.forum.database.PostRepository;
import com.kuba.forum.database.ThreadRepository;
import com.kuba.forum.database.TopicRepository;
import com.kuba.forum.model.Thread;
import com.kuba.forum.model.Topic;
import com.kuba.forum.model.view.FullTopicDTO;
import com.kuba.forum.services.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements ITopicService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    ThreadRepository threadRepository;

    @Override
    public List<Topic> getAllTopics() {
        return this.topicRepository.findAll();
    }

    @Override
    public List<FullTopicDTO> getForumContent() {
        return this.topicRepository.findAll().stream()
                .map(topic -> new FullTopicDTO(
                        topic,
                        this.threadRepository.countByTopicId(topic.getId())
                ))
                .toList();
    }

    @Override
    public Optional<Topic> findTopicById(int topicId) {
        return this.topicRepository.findById(topicId);
    }

    @Override
    public Topic addTopic(Topic topic) {
        return this.topicRepository.save(topic);
    }

    @Transactional
    @Override
    public void deleteTopic(int topicId) {
        List<Thread> threadList = this.threadRepository.findByTopicId(topicId);

        this.threadRepository.findByTopicId(topicId)
                .forEach(thread -> {
                    this.postRepository.deleteByThreadId(thread.getId());
                    this.threadRepository.deleteById(thread.getId());
                });

        this.topicRepository.deleteById(topicId);
    }

    @Override
    public void editTopic(Topic topic) {
        Optional<Topic> topicToUpdateBox = this.topicRepository.findById(topic.getId());
        if (topicToUpdateBox.isPresent()) {
            Topic updatedTopic = topicToUpdateBox.get();
            updatedTopic.setName(topic.getName());
            updatedTopic.setDescription(topic.getDescription());
            this.topicRepository.save(updatedTopic);
        }
    }
}

package com.kuba.forum.services.impl;

import com.kuba.forum.database.PostRepository;
import com.kuba.forum.database.ThreadRepository;
import com.kuba.forum.database.TopicRepository;
import com.kuba.forum.database.UserRepository;
import com.kuba.forum.model.Thread;
import com.kuba.forum.model.view.FullThreadDTO;
import com.kuba.forum.services.IThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ThreadServiceImpl implements IThreadService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    ThreadRepository threadRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<Thread> findThreadById(int threadId) {
        return this.threadRepository.findById(threadId);
    }

    @Override
    public Thread addThread(Thread thread) {
        thread.setCreationTime(LocalDateTime.now());
        return this.threadRepository.save(thread);
    }

    @Transactional
    @Override
    public void deleteThread(int threadId) {
        this.postRepository.deleteByThreadId(threadId);
        this.threadRepository.deleteById(threadId);
    }

    @Override
    public List<Thread> getThreadsInTopic(int topicId) {
        return threadRepository.findByTopicId(topicId);
    }

    @Override
    public List<FullThreadDTO> getTopicContent(final int topicId) {
        return this.threadRepository.findByTopicId(topicId).stream()
                .map(thread -> new FullThreadDTO(
                        thread,
                        this.topicRepository.findById(topicId).get(),
                        this.userRepository.findById(thread.getAuthorId()).get(),
                        this.postRepository.countByThreadId(thread.getId())
                ))
                .toList();
    }

    @Override
    public void editThread(Thread thread) {
        Optional<Thread> threadToUpdateBox = this.threadRepository.findById(thread.getId());
        if (threadToUpdateBox.isPresent()) {
            Thread updatedThread = threadToUpdateBox.get();
            updatedThread.setSubject(thread.getSubject());
            this.threadRepository.save(updatedThread);
        }
    }
}

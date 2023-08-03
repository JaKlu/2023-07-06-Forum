package com.kuba.forum.services.impl;

import com.kuba.forum.database.IThreadDAO;
import com.kuba.forum.database.ITopicDAO;
import com.kuba.forum.database.IUserDAO;
import com.kuba.forum.model.Thread;
import com.kuba.forum.model.view.FullThreadDTO;
import com.kuba.forum.services.IThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThreadServiceImpl implements IThreadService {
    @Autowired
    ITopicDAO topicDAO;
    @Autowired
    IThreadDAO threadDAO;
    @Autowired
    IUserDAO userDAO;

    @Override
    public Optional<Thread> findThreadById(int threadId) {
        return this.threadDAO.findThreadById(threadId);
    }

    @Override
    public Thread addThread(Thread thread) {
        return this.threadDAO.addThread(thread);
    }

    @Override
    public void deleteThread(int threadId) {
        this.threadDAO.deleteThread(threadId);
    }

    @Override
    public List<Thread> getThreadsInTopic(int topicId) {
        return threadDAO.getThreadsInTopic(topicId);
    }

    @Override
    public List<FullThreadDTO> getTopicContent(final int topicId) {
        return this.threadDAO.getThreadsInTopic(topicId).stream()
                .map(thread -> new FullThreadDTO(
                        thread,
                        this.topicDAO.findTopicById(topicId).get(),
                        this.userDAO.getUserById(thread.getAuthorId()).get(),
                        this.threadDAO.getNumberOfRepliesInThread(thread.getId())
                ))
                .toList();
    }

    @Override
    public void editThread(Thread thread) {
        Optional<Thread> threadToUpdateBox = this.threadDAO.findThreadById(thread.getId());
        if (threadToUpdateBox.isPresent()) {
            Thread updatedThread = threadToUpdateBox.get();
            updatedThread.setSubject(thread.getSubject());
            this.threadDAO.editThread(updatedThread);
        }
    }
}

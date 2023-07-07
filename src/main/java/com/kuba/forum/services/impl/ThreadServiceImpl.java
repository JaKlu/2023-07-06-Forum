package com.kuba.forum.services.impl;

import com.kuba.forum.database.IThreadDAO;
import com.kuba.forum.model.Post;
import com.kuba.forum.model.Thread;
import com.kuba.forum.services.IThreadService;
import com.kuba.forum.session.SessionData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreadServiceImpl implements IThreadService {
    @Autowired
    IThreadDAO threadDAO;

    @Override
    public Thread findThreadById(int threadId) {
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
}

package com.kuba.forum.services;

import com.kuba.forum.model.Post;
import com.kuba.forum.model.Thread;

import java.util.List;
import java.util.Optional;

public interface IThreadService {


    Thread addThread(Thread thread);

    List<Thread> getThreadsInTopic(int topicId);

    void deleteThread(int threadId);

    Optional<Thread> findThreadById(int threadId);

}

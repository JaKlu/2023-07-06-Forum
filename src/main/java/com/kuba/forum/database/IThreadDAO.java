package com.kuba.forum.database;

import com.kuba.forum.model.Post;
import com.kuba.forum.model.Thread;

import java.util.List;

public interface IThreadDAO {
    Thread addThread(Thread thread);

    List<Thread> getThreadsInTopic(int topicId);


    Thread findThreadById(int threadId);

    void deleteThread(int threadId);

    void editThread(Thread thread);
}

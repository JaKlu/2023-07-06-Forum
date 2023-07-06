package com.kuba.forum.database;

import com.kuba.forum.model.Post;
import com.kuba.forum.model.Thread;

import java.util.List;

public interface IThreadDAO {
    Thread addThread(Thread thread);

    List<Thread> getThreadsInTopic(int topicId);

    List<Post> getPostsInThread(int threadId);

    Thread findThreadById(int threadId);

    void deleteThread(Thread thread);

    void editThread(Thread thread);
}

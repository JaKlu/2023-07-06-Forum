package com.kuba.forum.services;

import com.kuba.forum.model.Post;
import com.kuba.forum.model.Thread;

import java.util.List;

public interface IThreadService {

    List<Post> getPostsInThread(int threadId);
    Thread addThread(Thread thread);

    List<Thread> getThreadsInTopic(int topicId);
    Thread findThreadById(int threadId);

}

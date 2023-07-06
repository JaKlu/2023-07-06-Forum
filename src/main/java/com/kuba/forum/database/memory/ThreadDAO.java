package com.kuba.forum.database.memory;

import com.kuba.forum.database.IPostDAO;
import com.kuba.forum.database.IThreadDAO;
import com.kuba.forum.database.IUserDAO;
import com.kuba.forum.database.sequences.IThreadSequence;
import com.kuba.forum.model.Post;
import com.kuba.forum.model.Thread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ThreadDAO implements IThreadDAO {
    @Autowired
    IUserDAO users;
    @Autowired
    IPostDAO posts;
    IThreadSequence threadSequence;
    private final List<Thread> threads = new ArrayList<>();

    public ThreadDAO(@Autowired IThreadSequence threadSequence) {
        this.threadSequence = threadSequence;

        this.threads.add(new Thread(threadSequence.getId(), 1, 3, "Kill Bill",
                ZonedDateTime.of(LocalDate.of(2023, 7, 1), LocalTime.of(12, 15, 10), ZoneId.of("Europe/Warsaw")), new ArrayList<>()));
        this.threads.add(new Thread(threadSequence.getId(), 2, 3, "Avengers",
                ZonedDateTime.of(LocalDate.of(2023, 7, 2), LocalTime.of(9, 30, 20), ZoneId.of("Europe/Warsaw")), new ArrayList<>()));
    }

    public List<Thread> getThreadsInTopic(int topicId) {
        List<Thread> threadsInTopic = new ArrayList<>();
        for (Thread thread : this.threads) {
            if (thread.getTopicId() == topicId) {
                threadsInTopic.add(thread);
            }
        }
        return threadsInTopic;
    }

    @Override
    public List<Post> getPostsInThread(int threadId) {
        return findThreadById(threadId).getPosts();
    }

    @Override
    public Thread findThreadById(int threadId) {
        for (Thread thread : this.threads) {
            if (thread.getId() == (threadId)) {
                return thread;
            }
        }
        return null;
    }

    @Override
    public Thread addThread(Thread thread) {
        thread.setId(threadSequence.getId());
        thread.setCreationTime(ZonedDateTime.now());
        this.threads.add(thread);
        return thread;
    }


    @Override
    public void deleteThread(Thread thread) {

    }

    @Override
    public void editThread(Thread thread) {

    }
}

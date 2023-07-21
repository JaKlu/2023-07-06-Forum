package com.kuba.forum.database.memory;

import com.kuba.forum.database.IPostDAO;
import com.kuba.forum.database.IThreadDAO;
import com.kuba.forum.database.sequences.IThreadSequence;
import com.kuba.forum.model.Thread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ThreadDAO implements IThreadDAO {
    @Autowired
    IPostDAO postDAO;
    IThreadSequence threadSequence;
    private final List<Thread> threads = new ArrayList<>();

    public ThreadDAO(@Autowired IThreadSequence threadSequence) {
        this.threadSequence = threadSequence;

        this.threads.add(new Thread(threadSequence.getId(), 1, 3, "Kill Bill",
                ZonedDateTime.of(LocalDate.of(2023, 7, 1),
                        LocalTime.of(12, 15, 10), ZoneId.of("Europe/Warsaw"))));
        this.threads.add(new Thread(threadSequence.getId(), 2, 3, "Avengers",
                ZonedDateTime.of(LocalDate.of(2023, 7, 2),
                        LocalTime.of(9, 30, 20), ZoneId.of("Europe/Warsaw"))));
        this.threads.add(new Thread(threadSequence.getId(), 1, 1, "Witam na forum",
                ZonedDateTime.of(LocalDate.of(2023, 7, 1),
                        LocalTime.of(8, 0, 0), ZoneId.of("Europe/Warsaw"))));
        this.threads.add(new Thread(threadSequence.getId(), 2, 1, "Cześć wszystkim!!!",
                ZonedDateTime.of(LocalDate.of(2023, 7, 2),
                        LocalTime.of(15, 12, 0), ZoneId.of("Europe/Warsaw"))));
        this.threads.add(new Thread(threadSequence.getId(), 3, 1, "Pozdrowienia z Warszawy :)",
                ZonedDateTime.of(LocalDate.of(2023, 7, 3),
                        LocalTime.of(9, 7, 0), ZoneId.of("Europe/Warsaw"))));
        this.threads.add(new Thread(threadSequence.getId(), 4, 1, "Eluwina, tu Ania z Gdańska",
                ZonedDateTime.of(LocalDate.of(2023, 7, 4),
                        LocalTime.of(8, 15, 0), ZoneId.of("Europe/Warsaw"))));
    }

    public List<Thread> getThreadsInTopic(final int topicId) {
        return this.threads.stream()
                .filter(thread -> thread.getTopicId() == topicId)
                .toList();
    }

    @Override
    public Optional<Thread> findThreadById(final int threadId) {
        return this.threads.stream()
                .filter(thread -> thread.getId() == (threadId))
                .findFirst();
    }

    @Override
    public Thread addThread(Thread thread) {
        thread.setId(threadSequence.getId());
        thread.setCreationTime(ZonedDateTime.now());
        this.threads.add(thread);
        return thread;
    }


    @Override
    public void deleteThread(int threadId) {
        Optional<Thread> threadBox = findThreadById(threadId);
        if (threadBox.isPresent()) {
            this.threads.remove(threadBox.get());
            this.postDAO.deleteAllPostsFromThread(threadId);
        }
    }

    //TODO edycja tematów
    @Override
    public void editThread(Thread thread) {

    }

    @Override
    public int getNumberOfRepliesInThread(int threadId) {
        return this.postDAO.getPostsFromThread(threadId).size();
    }
}

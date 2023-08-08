package com.kuba.forum.database.hibernate;

import com.kuba.forum.database.IPostDAO;
import com.kuba.forum.database.IThreadDAO;
import com.kuba.forum.model.Thread;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ThreadDAO implements IThreadDAO {
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    IPostDAO postDAO;

    @Override
    public Thread addThread(Thread thread) {
        return mergeThread(thread);
    }

    @Override
    public List<Thread> getThreadsInTopic(int topicId) {
        Session session = this.sessionFactory.openSession();
        Query<Thread> query = session.createQuery("FROM com.kuba.forum.model.Thread WHERE topicId = :topicId", Thread.class);
        query.setParameter("topicId", topicId);
        List<Thread> resultList = query.getResultList();
        session.close();
        return resultList;
    }

    @Override
    public Optional<Thread> findThreadById(int threadId) {
        Session session = this.sessionFactory.openSession();
        Query<Thread> query = session.createQuery("FROM com.kuba.forum.model.Thread WHERE id = :threadId", Thread.class);
        query.setParameter("threadId", threadId);
        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    @Override
    public void editThread(Thread thread) {
        mergeThread(thread);
    }

    @Override
    public int getNumberOfRepliesInThread(int threadId) {
        Session session = sessionFactory.openSession();

        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM com.kuba.forum.model.Post WHERE threadId = :threadId", Long.class);
        query.setParameter("threadId", threadId);
        long result = query.uniqueResult();
        session.close();

        return (int) Math.max(result - 1, 0);
    }

    @Override
    public void deleteThread(int threadId) {
        Session session = this.sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.remove(new Thread(threadId));
            this.postDAO.deleteAllPostsFromThread(threadId);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    private Thread mergeThread(Thread thread) {  //TODO returning thread?
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            thread.setCreationTime(LocalDateTime.now());
            thread = session.merge(thread);
            session.getTransaction().commit();
            return thread;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }
}

package com.kuba.forum.database.hibernate;

import com.kuba.forum.database.IThreadDAO;
import com.kuba.forum.database.ITopicDAO;
import com.kuba.forum.model.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TopicDAO implements ITopicDAO {
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    IThreadDAO threadDAO;

    @Override
    public Topic addTopic(Topic topic) {
        return mergeTopic(topic);
    }

    @Override
    public List<Topic> getAllTopics() {
        Session session = this.sessionFactory.openSession();
        Query<Topic> query = session.createQuery("FROM com.kuba.forum.model.Topic", Topic.class);
        List<Topic> resultList = query.getResultList();
        session.close();
        return resultList;
    }

    @Override
    public Optional<Topic> findTopicById(int topicId) {
        Session session = this.sessionFactory.openSession();
        Query<Topic> query = session.createQuery("FROM com.kuba.forum.model.Topic WHERE id = :topicId", Topic.class);
        query.setParameter("topicId", topicId);
        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    @Override
    public int getNumberOfThreadsInTopic(int topicId) {
        Session session = sessionFactory.openSession();

        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM com.kuba.forum.model.Thread WHERE topicId = :topicId", Long.class);
        query.setParameter("topicId", topicId);
        long result = query.uniqueResult();
        session.close();

        return (int) result;
    }

    @Override
    public void deleteTopic(int topicId) {
        Session session = this.sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.remove(new Topic(topicId));
            this.threadDAO.getThreadsInTopic(topicId)
                    .forEach(thread -> this.threadDAO.deleteThread(thread.getId()));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void editTopic(Topic topic) {
        mergeTopic(topic);
    }

    private Topic mergeTopic(Topic topic) {  //TODO returning post?
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            topic = session.merge(topic);
            session.getTransaction().commit();
            return topic;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }
}

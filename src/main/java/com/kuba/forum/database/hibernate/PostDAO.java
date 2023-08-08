package com.kuba.forum.database.hibernate;

import com.kuba.forum.database.IPostDAO;
import com.kuba.forum.model.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class PostDAO implements IPostDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Post addPost(Post post) {
        return mergePost(post);
    }

    @Override
    public List<Post> getAllPosts() {
        Session session = this.sessionFactory.openSession();
        Query<Post> query = session.createQuery("FROM com.kuba.forum.model.Post", Post.class);
        List<Post> resultList = query.getResultList();
        session.close();
        return resultList;
    }

    @Override
    public List<Post> getQueriedPosts(String pattern) {
        Session session = this.sessionFactory.openSession();
        Query<Post> query = session.createQuery("FROM com.kuba.forum.model.Post WHERE contents LIKE :pattern", Post.class);
        query.setParameter("pattern", "%" + pattern + "%");
        List<Post> resultList = query.getResultList();
        session.close();
        return resultList;
    }

    @Override
    public List<Post> getPostsFromThread(int threadId) {
        Session session = this.sessionFactory.openSession();
        Query<Post> query = session.createQuery("FROM com.kuba.forum.model.Post WHERE threadId = :threadId", Post.class);
        query.setParameter("threadId", threadId);
        List<Post> resultList = query.getResultList();
        session.close();
        return resultList;
    }

    @Override
    public List<Post> getAllUserPosts(int userId) {
        Session session = this.sessionFactory.openSession();
        Query<Post> query = session.createQuery("FROM com.kuba.forum.model.Post WHERE authorId = :userId", Post.class);
        query.setParameter("userId", userId);
        List<Post> resultList = query.getResultList();
        session.close();
        return resultList;
    }

    @Override
    public Optional<Post> getPostById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Post> query = session.createQuery("FROM com.kuba.forum.model.Post WHERE id = :id", Post.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    @Override
    public void editPost(Post post) {
        mergePost(post);
    }

    @Override
    public void deleteAllPostsFromThread(int threadId) {
/*        Session session = sessionFactory.openSession();

        Query<Post> query = session.createQuery("DELETE com.kuba.forum.model.Post WHERE threadId = :threadId", Post.class);
        query.setParameter("threadId", threadId);
        int result = query.executeUpdate();
        session.close();
        System.out.println(result);*/
        List<Post> postsFromThread = getPostsFromThread(threadId);

        Session session = this.sessionFactory.openSession();

        try {
            session.beginTransaction();
            postsFromThread.forEach(post -> deletePost(post.getId()));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public boolean deletePost(int postId) {
        Session session = this.sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.remove(new Post(postId));
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }
    }

    private Post mergePost(Post post) {  //TODO returning post?
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            post.setCreationTime(LocalDateTime.now());
            post = session.merge(post);
            session.getTransaction().commit();
            return post;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }
}

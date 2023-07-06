package com.kuba.forum.database.memory;

import com.kuba.forum.database.IPostDAO;
import com.kuba.forum.database.IUserDAO;
import com.kuba.forum.database.sequences.IPostSequence;
import com.kuba.forum.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Repository
public class PostDAO implements IPostDAO {

    IPostSequence postSequence;
    @Autowired
    IUserDAO userDAO;
    private final List<Post> posts = new ArrayList<>();

    public PostDAO(@Autowired IPostSequence postSequence) {
        this.posts.add(new Post(postSequence.getId(), 1, 1, ZonedDateTime.of(LocalDate.of(
                2023, 7, 1), LocalTime.of(12, 15, 10), ZoneId.of("Europe/Warsaw")),
                "Uważam, że Kill Bill to słaby film, bo za mało w nim szybkich samochodów"));
        this.posts.add(new Post(postSequence.getId(), 1, 2, ZonedDateTime.of(LocalDate.of(
                2023, 7, 1), LocalTime.of(12, 20, 10), ZoneId.of("Europe/Warsaw")),
                "Kolego, chyba nie zrozumiałeś przesłania tego filmu - tam nie ma być autek tylko mordobicie"));
        this.posts.add(new Post(postSequence.getId(), 2, 2, ZonedDateTime.of(LocalDate.of(
                2023, 7, 2), LocalTime.of(9, 30, 20), ZoneId.of("Europe/Warsaw")),
                "Avengersi - to jest zajebisty film!"));
        this.posts.add(new Post(postSequence.getId(), 2, 1, ZonedDateTime.of(LocalDate.of(
                2023, 7, 2), LocalTime.of(9, 40, 20), ZoneId.of("Europe/Warsaw")),
                "Avengersi to zło w najczystszej postaci - szatani!"));

        this.postSequence = postSequence;
    }

    public List<Post> getAllPosts() {
        return new ArrayList<>(this.posts);
    }

    public List<Post> getPostsFromThread(int threadId) {
        List<Post> postsFromThread = new ArrayList<>();
        for (Post post : posts) {
            if (post.getThreadId() == threadId) {
                postsFromThread.add(post);
            }
        }
        return postsFromThread;
    }

    @Override
    public Post addPost(Post post) {
        post.setId(postSequence.getId());
        post.setCreationTime(ZonedDateTime.now());
        this.posts.add(post);
        this.userDAO.increaseNumberOfPosts(this.userDAO.getUserById(post.getAuthorId()));
        return post;
    }

    @Override
    public Post getPostById(int id) {
        for (Post post : this.posts) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    @Override
    public void editPost(Post post) {
        Iterator<Post> iterator = this.posts.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == post.getId()) {
                iterator.remove();
                break;
            }
        }
        this.posts.add(post);
    }

    @Override
    public void deletePost(int id) {
        Iterator<Post> iterator = this.posts.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                return;
            }
        }
    }
}

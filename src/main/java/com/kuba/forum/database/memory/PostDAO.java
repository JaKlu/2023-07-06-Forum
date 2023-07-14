package com.kuba.forum.database.memory;

import com.kuba.forum.database.IPostDAO;
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

    private final List<Post> posts = new ArrayList<>();

    public PostDAO(@Autowired IPostSequence postSequence) {
        this.posts.add(new Post(postSequence.getId(), 1, 1, ZonedDateTime.of(LocalDate.of(
                2023, 7, 1), LocalTime.of(12, 15, 10), ZoneId.of("Europe/Warsaw")),
                "Uważam, że Kill Bill to słaby film, bo za mało w nim \nszybkich samochodów"));
        this.posts.add(new Post(postSequence.getId(), 1, 2, ZonedDateTime.of(LocalDate.of(
                2023, 7, 2), LocalTime.of(12, 20, 10), ZoneId.of("Europe/Warsaw")),
                "Kolego, chyba nie zrozumiałeś przesłania tego filmu - tam nie ma być autek tylko mordobicie"));
        this.posts.add(new Post(postSequence.getId(), 2, 2, ZonedDateTime.of(LocalDate.of(
                2023, 7, 2), LocalTime.of(9, 30, 20), ZoneId.of("Europe/Warsaw")),
                "Avengersi - to jest zajebisty film!"));
        this.posts.add(new Post(postSequence.getId(), 2, 1, ZonedDateTime.of(LocalDate.of(
                2023, 7, 2), LocalTime.of(9, 40, 20), ZoneId.of("Europe/Warsaw")),
                "Avengersi to zło w najczystszej postaci - szatani!"));
        this.posts.add(new Post(postSequence.getId(), 3, 1, ZonedDateTime.of(LocalDate.of(
                2023, 7, 2), LocalTime.of(15, 12, 0), ZoneId.of("Europe/Warsaw")),
                "Witajcie na nowym forum poświęconym szeroko pojętej filmografii. Dobrej zabawy!"));
        this.posts.add(new Post(postSequence.getId(), 3, 2, ZonedDateTime.of(LocalDate.of(
                2023, 7, 2), LocalTime.of(15, 12, 0), ZoneId.of("Europe/Warsaw")),
                "Hej, fajne miejsce."));
        this.posts.add(new Post(postSequence.getId(), 3, 3, ZonedDateTime.of(LocalDate.of(
                2023, 7, 3), LocalTime.of(15, 12, 0), ZoneId.of("Europe/Warsaw")),
                "Na pewno będę częstym gościem."));
        this.posts.add(new Post(postSequence.getId(), 3, 4, ZonedDateTime.of(LocalDate.of(
                2023, 7, 4), LocalTime.of(15, 12, 0), ZoneId.of("Europe/Warsaw")),
                "Ale super forum. Nie pozbędziecie się mnie zbyt szybko <3"));
        this.posts.add(new Post(postSequence.getId(), 4, 2, ZonedDateTime.of(LocalDate.of(
                2023, 7, 2), LocalTime.of(15, 12, 0), ZoneId.of("Europe/Warsaw")),
                "Cześć przyjaciele filmożercy. Pozdrawiam z byłej już stolicy - Krakowa!"));
        this.posts.add(new Post(postSequence.getId(), 5, 3, ZonedDateTime.of(LocalDate.of(
                2023, 7, 3), LocalTime.of(15, 12, 0), ZoneId.of("Europe/Warsaw")),
                "RAZ RAZ PRÓBA MIKROFONU - Wita was Wojtas z WWA"));
        this.posts.add(new Post(postSequence.getId(), 6, 4, ZonedDateTime.of(LocalDate.of(
                2023, 7, 4), LocalTime.of(15, 12, 0), ZoneId.of("Europe/Warsaw")),
                "Siemaneczko słodziaczki moje kochane pierożki nie-ruskie z masełkiem. BUZIACZKI!"));

        this.postSequence = postSequence;
    }

    @Override
    public List<Post> getAllPosts() {
        return new ArrayList<>(this.posts);
    }

    public List<Post> getQueriedPosts(String query) {
        List<Post> posts = getAllPosts();
        List<Post> queriedPosts = new ArrayList<>();

        for (Post post : posts) {
            if (post.getContents().toLowerCase().contains(query.toLowerCase())) {
                queriedPosts.add(post);
            }
        }

        return queriedPosts;
    }

    @Override
    public List<Post> getAllUserPosts(int userId) {
        List<Post> allUserPosts = new ArrayList<>();
        for (Post post : posts) {
            if (post.getAuthorId() == userId) {
                allUserPosts.add(post);
            }
        }
        return allUserPosts;
    }

    @Override
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
    public void deletePost(int postId) {
        Iterator<Post> iterator = this.posts.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == postId) {
                iterator.remove();
                return;
            }
        }
    }

    @Override
    public void deleteAllPostsFromThread(int threadId) {
        List<Post> threadToDelete = getPostsFromThread(threadId);
        for (Post post : threadToDelete) {
            deletePost(post.getId());
        }
    }
}

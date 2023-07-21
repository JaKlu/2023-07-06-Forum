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
        this.postSequence = postSequence;

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
    }

    @Override
    public List<Post> getAllPosts() {
        return new ArrayList<>(this.posts);
    }

    public List<Post> getQueriedPosts(final String query) {
        return this.posts.stream()
                .filter(post -> post.getContents().toLowerCase().contains(query.toLowerCase()))
                .toList();
    }

    @Override
    public List<Post> getAllUserPosts(final int userId) {
        return this.posts.stream()
                .filter(post -> post.getAuthorId() == userId)
                .toList();
    }

    @Override
    public List<Post> getPostsFromThread(final int threadId) {
        return this.posts.stream()
                .filter(post -> post.getThreadId() == threadId)
                .toList();
    }

    @Override
    public Post addPost(Post post) {
        post.setId(postSequence.getId());
        post.setCreationTime(ZonedDateTime.now());
        this.posts.add(post);
        return post;
    }

    @Override
    public Optional<Post> getPostById(final int id) {
        return this.posts.stream()
                .filter(post -> post.getId() == id)
                .findFirst();
    }

    //TODO edycja postów
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
        Optional<Post> postBox = getPostById(postId);
        if (postBox.isPresent()) {
            this.posts.remove(postBox.get());
        }
    }

    @Override
    public void deleteAllPostsFromThread(final int threadId) {

        //working stream
        //List<Post> threadToDelete = getPostsFromThread(threadId);
        List<Post> threadToDelete = this.posts.stream()
                .filter(post -> post.getThreadId() == threadId)
                .toList();
        threadToDelete.stream()
                .forEach(post -> deletePost(post.getId()));
        //TODO dlaczego nie działa?
        //not working stream
/*        this.posts.stream()
                .filter(post -> post.getThreadId() == threadId)
                .forEach(post -> deletePost(post.getId()));*/

//        List<Post> threadToDelete = getPostsFromThread(threadId);
//        for (Post post : threadToDelete) {
//            deletePost(post.getId());
//        }
    }
}

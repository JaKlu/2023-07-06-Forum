package com.kuba.forum.database.memory;

import com.kuba.forum.database.IPostDAO;
import com.kuba.forum.database.sequences.IPostSequence;
import com.kuba.forum.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.*;
import java.util.*;

public class PostDAO implements IPostDAO {

    IPostSequence postSequence;

    private final List<Post> posts = new ArrayList<>();

    public PostDAO(@Autowired IPostSequence postSequence) {
        this.postSequence = postSequence;

        this.posts.add(new Post(postSequence.getId(), 1, 1, LocalDateTime.of(LocalDate.of(
                2023, 7, 1), LocalTime.of(12, 15, 10)),
                "Uważam, że Kill Bill to słaby film, bo za mało w nim \nszybkich samochodów"));
        this.posts.add(new Post(postSequence.getId(), 1, 2, LocalDateTime.of(LocalDate.of(
                2023, 7, 2), LocalTime.of(12, 20, 10)),
                "Kolego, chyba nie zrozumiałeś przesłania tego filmu - tam nie ma być autek tylko mordobicie"));
        this.posts.add(new Post(postSequence.getId(), 2, 2, LocalDateTime.of(LocalDate.of(
                2023, 7, 2), LocalTime.of(9, 30, 20)),
                "Avengersi - to jest zajebisty film!"));
        this.posts.add(new Post(postSequence.getId(), 2, 1, LocalDateTime.of(LocalDate.of(
                2023, 7, 2), LocalTime.of(9, 40, 20)),
                "Avengersi to zło w najczystszej postaci - szatani!"));
        this.posts.add(new Post(postSequence.getId(), 3, 1, LocalDateTime.of(LocalDate.of(
                2023, 7, 2), LocalTime.of(15, 12, 0)),
                "Witajcie na nowym forum poświęconym szeroko pojętej filmografii. Dobrej zabawy!"));
        this.posts.add(new Post(postSequence.getId(), 3, 2, LocalDateTime.of(LocalDate.of(
                2023, 7, 2), LocalTime.of(15, 12, 2)),
                "Hej, fajne miejsce."));
        this.posts.add(new Post(postSequence.getId(), 3, 3, LocalDateTime.of(LocalDate.of(
                2023, 7, 3), LocalTime.of(15, 12, 4)),
                "Na pewno będę częstym gościem."));
        this.posts.add(new Post(postSequence.getId(), 3, 4, LocalDateTime.of(LocalDate.of(
                2023, 7, 4), LocalTime.of(15, 12, 8)),
                "Ale super forum. Nie pozbędziecie się mnie zbyt szybko <3"));
        this.posts.add(new Post(postSequence.getId(), 4, 2, LocalDateTime.of(LocalDate.of(
                2023, 7, 2), LocalTime.of(15, 12, 12)),
                "Cześć przyjaciele filmożercy. Pozdrawiam z byłej już stolicy - Krakowa!"));
        this.posts.add(new Post(postSequence.getId(), 5, 3, LocalDateTime.of(LocalDate.of(
                2023, 7, 3), LocalTime.of(15, 12, 20)),
                "RAZ RAZ PRÓBA MIKROFONU - Wita was Wojtas z WWA"));
        this.posts.add(new Post(postSequence.getId(), 6, 4, LocalDateTime.of(LocalDate.of(
                2023, 7, 4), LocalTime.of(15, 12, 30)),
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
        post.setCreationTime(LocalDateTime.now());
        this.posts.add(post);
        return post;
    }

    @Override
    public Optional<Post> getPostById(final int id) {
        return this.posts.stream()
                .filter(post -> post.getId() == id)
                .findFirst();
    }

    @Override
    public void editPost(Post post) {
        if (deletePost(post.getId())) {
            this.posts.add(post);
        }
    }

    @Override
    public boolean deletePost(int postId) {
        Optional<Post> postBox = getPostById(postId);
        if (postBox.isPresent()) {
            this.posts.remove(postBox.get());
            return true;
        }
        return false;
    }

    @Override
    public void deleteAllPostsFromThread(final int threadId) {

        //working stream
//        List<Post> threadToDelete = this.posts.stream()
//                .filter(post -> post.getThreadId() == threadId)
//                .toList();
//        threadToDelete
//                .forEach(post -> deletePost(post.getId()));

        List<Post> threadToDelete = getPostsFromThread(threadId);
        this.posts.removeAll(threadToDelete);


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

package com.kuba.forum.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity(name = "tpost")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "thread_id")
    private int threadId;

    @Column(name = "author_id")
    private int authorId;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(length = 10000, name = "contents")
    private String contents;

    public Post(int id) {
        this.id = id;
    }

    public static Post copyOf(Post post) {
        Post copiedPost = new Post();
        copiedPost.id = post.id;
        copiedPost.threadId = post.threadId;
        copiedPost.authorId = post.authorId;
        copiedPost.creationTime = post.creationTime;
        copiedPost.contents = post.contents;
        return copiedPost;
    }

    public String getPrettyDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return this.creationTime.format(formatter);
    }
}

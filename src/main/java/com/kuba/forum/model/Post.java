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
    private int id;
    private int threadId;
    private int authorId;
    private LocalDateTime creationTime;
    @Column(length = 10000)
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

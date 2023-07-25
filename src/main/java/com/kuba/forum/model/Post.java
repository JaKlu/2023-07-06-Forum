package com.kuba.forum.model;

import lombok.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Post {
    private int id;
    private int threadId;
    private int authorId;
    private ZonedDateTime creationTime;
    private String contents;

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

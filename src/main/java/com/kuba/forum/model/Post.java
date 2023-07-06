package com.kuba.forum.model;

import lombok.*;

import java.time.ZonedDateTime;

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
}

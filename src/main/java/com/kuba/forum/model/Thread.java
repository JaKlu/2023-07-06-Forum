package com.kuba.forum.model;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Thread {
    private int id;
    private int authorId;
    private int topicId;
    private String subject;
    private ZonedDateTime creationTime;
    private List<Post> posts = new ArrayList<>();
}

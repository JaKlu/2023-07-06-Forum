package com.kuba.forum.model;

import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

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
    private LocalDateTime creationTime;
}

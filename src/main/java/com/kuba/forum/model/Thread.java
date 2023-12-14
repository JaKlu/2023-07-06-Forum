package com.kuba.forum.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity(name = "tthread")
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "author_id")
    private int authorId;

    @Column(name = "topic_id")
    private int topicId;

    @Column(name = "subject")
    private String subject;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    public Thread(int id) {
        this.id = id;
    }
}

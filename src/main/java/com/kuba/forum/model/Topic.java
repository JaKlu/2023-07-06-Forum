package com.kuba.forum.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Topic {
    private int id;
    private String name;
    private String description;
//    private List<Thread> threads;
}

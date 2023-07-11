package com.kuba.forum.model;

import lombok.*;

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
}

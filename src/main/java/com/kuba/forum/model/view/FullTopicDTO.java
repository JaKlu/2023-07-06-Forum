package com.kuba.forum.model.view;

import com.kuba.forum.model.Topic;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FullTopicDTO {
    private Topic topic;
    private int numberOfThreads;
}

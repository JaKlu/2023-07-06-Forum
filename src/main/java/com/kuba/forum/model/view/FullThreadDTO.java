package com.kuba.forum.model.view;

import com.kuba.forum.model.Thread;
import com.kuba.forum.model.Topic;
import com.kuba.forum.model.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FullThreadDTO {
    private Thread thread;
    private Topic topic;
    private User user;
    private int numberOfReplies;
}

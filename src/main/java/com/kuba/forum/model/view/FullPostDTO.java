package com.kuba.forum.model.view;

import com.kuba.forum.model.Post;
import com.kuba.forum.model.Thread;
import com.kuba.forum.model.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FullPostDTO {
    private Post post;
    private Thread thread;
    private User user;
    private int numberOfUserPosts;
}

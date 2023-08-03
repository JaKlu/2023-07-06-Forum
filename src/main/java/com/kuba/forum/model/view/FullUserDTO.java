package com.kuba.forum.model.view;

import com.kuba.forum.model.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FullUserDTO {
    private User user;
    private int numberOfPosts;
}

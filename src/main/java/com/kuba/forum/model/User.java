package com.kuba.forum.model;

import lombok.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    private int id;
    private String login;
    private String password;
    private String email;
    private LocalDate birthday;
    private Gender gender;
    private ZonedDateTime joinDate;
    private int numberOfPosts;
    private String place;
    private Function function;


    public static User copyOf(User user) {
        User copiedUser = new User();
        copiedUser.id = user.id;
        copiedUser.login = user.login;
        copiedUser.password = user.password;
        copiedUser.email = user.email;
        copiedUser.birthday = user.birthday;
        copiedUser.gender = user.gender;
        copiedUser.joinDate = user.joinDate;
        copiedUser.numberOfPosts = user.numberOfPosts;
        copiedUser.place = user.place;
        copiedUser.function = user.function;
        return copiedUser;
    }


    public enum Function {
        ADMIN,
        USER
    }

    public enum Gender {
        MALE,
        FEMALE
    }

}

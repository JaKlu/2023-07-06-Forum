package com.kuba.forum.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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
    private LocalDateTime joinDate;
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
        copiedUser.place = user.place;
        copiedUser.function = user.function;
        return copiedUser;
    }

    public String getPrettyJoinDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return this.joinDate.format(formatter);
    }

    public int getAge() {
        return (int) java.time.temporal.ChronoUnit.YEARS.between(this.birthday, ZonedDateTime.now());
    }

    public enum Function {
        ADMIN("Administrator"),
        USER("Użytkownik");
        private final String name;

        Function(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }
    }

    public enum Gender {
        MALE("Mężczyzna"),
        FEMALE("Kobieta");
        private final String name;

        Gender(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }
    }
}

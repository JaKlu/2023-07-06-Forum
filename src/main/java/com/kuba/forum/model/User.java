package com.kuba.forum.model;

import jakarta.persistence.*;
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
@Entity(name = "tuser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private int id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "join_date")
    private LocalDateTime joinDate;

    @Column(name = "place")
    private String place;

    @Enumerated(EnumType.STRING)
    @Column(name = "function_type")
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

    public String getPrettyBirthday() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return this.birthday.format(formatter);
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

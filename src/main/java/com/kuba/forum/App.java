package com.kuba.forum;

import com.kuba.forum.model.User;
import com.kuba.forum.validators.UserValidator;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class App {
    public static void main(String[] args) {


        System.out.println(DigestUtils.md5Hex("ania"));

/*        User user = new User(1,
                "4hgfgfg", "21232f297a57a5a743894a0e4a801fc3", "admin@filmovie.com",
                LocalDate.of(1989, 5, 28), User.Gender.MALE,
                ZonedDateTime.of(LocalDate.of(2023, 7, 1),
                        LocalTime.of(12, 0, 0), ZoneId.of("Europe/Warsaw")),
                "Rzeszów", User.Function.ADMIN);
        UserValidator.validateUser(user);*/

        System.out.println(User.Function.ADMIN.name());
        System.out.println(User.Function.ADMIN);
        System.out.println(User.Function.ADMIN.toString());
    }

}

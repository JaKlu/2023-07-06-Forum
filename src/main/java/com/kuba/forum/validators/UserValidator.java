package com.kuba.forum.validators;

import com.kuba.forum.exceptions.UserValidationException;
import com.kuba.forum.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    public static void validateLogin(String login) {
        String regex = "^.{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(login);
        if (!matcher.matches()) {
            throw new UserValidationException();
        }
    }

    public static void validatePassword(String password) {
        String regex = "^.{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new UserValidationException();
        }
    }

    public static void validateEmail(String email) {
        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new UserValidationException();
        }
    }

    public static void validateBirthday(String birthday) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(birthday);
        if (!matcher.matches()) {
            throw new UserValidationException();
        }
    }

    public static void validateGender(String gender) {
        boolean isValid = false;
        User.Gender[] genders = User.Gender.values();
        for (User.Gender genderToCheck : genders) {
            if (genderToCheck.toString().equals(gender)) {
                isValid = true;
            }
        }
        if (!isValid) {
            throw new UserValidationException();
        }
    }

    public static void validatePlace(String place) {
        String regex = "^.+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(place);
        if (!matcher.matches()) {
            throw new UserValidationException();
        }
    }

    public static void validateFunction(String function) {
        boolean isValid = false;
        User.Function[] functions = User.Function.values();
        for (User.Function functionToCheck : functions) {
            if (functionToCheck.toString().equals(function)) {
                isValid = true;
            }
        }
        if (!isValid) {
            throw new UserValidationException();
        }
    }

    public static void validatePasswordEquality(String pass1, String pass2) {
        if (!pass1.equals(pass2)) {
            throw new UserValidationException();
        }
    }

    public static void validateUser(User user) {
        validateLogin(user.getLogin());
        validatePassword(user.getPassword());
        validateGender(String.valueOf(user.getGender()));
        validateBirthday(user.getBirthday().toString());
        validateEmail(user.getEmail());
        validatePlace(user.getPlace());
    }
}

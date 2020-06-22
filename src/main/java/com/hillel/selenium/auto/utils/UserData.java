package com.hillel.selenium.auto.utils;

import com.github.javafaker.Faker;
import com.hillel.selenium.auto.user.User;

public class UserData {

    public static User defaultUser() {
        return new User("evgenpetrovich4273", "evgenpetrovich4273@mail.com", "test123321123");
    }

    public static User randomUser() {
        Faker faker = new Faker();
        User user = new User();
        user.setUsername(faker.name().username());
        user.setEmail(faker.name().lastName() + "@mail.com");
        user.setPassword("test123321123");
        return user;
    }
}

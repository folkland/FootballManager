package ru.folkland.generate;

import com.github.javafaker.Faker;

class FakerGenerate {

    static String getFirstName() {
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    static String getLastName() {
        Faker faker = new Faker();
        return faker.name().lastName();
    }

    static String clubName() {
        Faker faker = new Faker();
        return faker.ancient().hero();
    }
}

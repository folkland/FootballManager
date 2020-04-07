package ru.folkland.manager.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Класс модель для описания человека
 *
 * @author folkland
 */
@Getter
@EqualsAndHashCode
@ToString
public abstract class Person {

    private static int count = 0;

    private int id;

    private String name;
    private String surname;

    private int age;

    public Person(String name, String surname, int age) {
        id = count;
        this.name = name;
        this.surname = surname;
        this.age = age;
        count++;
    }
}

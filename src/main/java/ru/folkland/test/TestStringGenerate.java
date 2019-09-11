package ru.folkland.test;

import ru.folkland.generate.StringGenerate;

import java.security.SecureRandom;

/**
 * @author folkland
 */
public class TestStringGenerate {

    public static void main(String[] args) {
        int c = 10;
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < c; i++) {
            System.out.println(StringGenerate.getName(random.nextInt(8) + 2));
        }
    }
}

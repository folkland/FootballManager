package ru.folkland.generate;

import java.security.SecureRandom;
import java.util.Locale;

public class StringGenerate {

    private static final String UPPER_VOWELS = "AEIOUY";
    private static final String UPPER_CONSONANTS = "BCDFGHJKLMNPQRSTVWXZ";
    private static final String LOVER_VOWELS = UPPER_VOWELS.toLowerCase(Locale.ROOT);
    private static final String LOVER_CONSONANTS = UPPER_CONSONANTS.toLowerCase(Locale.ROOT);

    private SecureRandom random = new SecureRandom();

    public static String getName(int length) {
        return "";
    }

    private char getVowels(boolean register) {
        if (register) return UPPER_VOWELS.charAt(random.nextInt(UPPER_VOWELS.length()));
        return LOVER_VOWELS.charAt(random.nextInt(LOVER_VOWELS.length()));
    }

    private char getConsonants(boolean register) {
        if (register) return UPPER_CONSONANTS.charAt(random.nextInt(UPPER_CONSONANTS.length()));
        return LOVER_CONSONANTS.charAt(random.nextInt(LOVER_CONSONANTS.length()));
    }
}

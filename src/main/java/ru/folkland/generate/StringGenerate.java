package ru.folkland.generate;

import ru.folkland.constants.Constants;

import java.security.SecureRandom;
import java.util.Locale;

public class StringGenerate {

    private static final String UPPER_VOWELS = "AEIOUY";
    private static final String UPPER_CONSONANTS = "BCDFGHJKLMNPQRSTVWXZ";
    private static final String LOVER_VOWELS = UPPER_VOWELS.toLowerCase(Locale.ROOT);
    private static final String LOVER_CONSONANTS = UPPER_CONSONANTS.toLowerCase(Locale.ROOT);

    public static String getName(int length) {
        StringBuilder name = new StringBuilder();
        boolean isUpper = true;
        boolean isVowel = Constants.RANDOM.nextBoolean();
        for (int i = 0; i < length; i++) {
            if (isVowel) name.append(getVowels(isUpper));
            else name.append(getConsonants(isUpper));
            isVowel = getOrder(isVowel);
            isUpper = false;
        }
        return name.toString();
    }

    private static char getVowels(boolean register) {
        if (register) return UPPER_VOWELS.charAt(Constants.RANDOM.nextInt(UPPER_VOWELS.length()));
        return LOVER_VOWELS.charAt(Constants.RANDOM.nextInt(LOVER_VOWELS.length()));
    }

    private static char getConsonants(boolean register) {
        if (register) return UPPER_CONSONANTS.charAt(Constants.RANDOM.nextInt(UPPER_CONSONANTS.length()));
        return LOVER_CONSONANTS.charAt(Constants.RANDOM.nextInt(LOVER_CONSONANTS.length()));
    }

    private static boolean getOrder(boolean isVowels) {
        if (isVowels) return false;
        return Constants.RANDOM.nextBoolean();
    }
}

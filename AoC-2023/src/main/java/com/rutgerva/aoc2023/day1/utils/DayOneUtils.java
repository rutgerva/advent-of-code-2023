package com.rutgerva.aoc2023.day1.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayOneUtils {

    public enum Digit {
        ZERO("zero", 0),
        ONE("one", 1),
        TWO("two", 2),
        THREE("three", 3),
        FOUR("four", 4),
        FIVE("five", 5),
        SIX("six", 6),
        SEVEN("seven", 7),
        EIGHT("eight", 8),
        NINE("nine", 9);

        Digit(String digitAsString, Integer digitAsInt) {
            this.name = digitAsString;
            this.value = digitAsInt;
        }

        private final String name;
        private final int value;

        public Integer getValue() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }
    }

    /**
     * Method checks for first numerical digit in a String
     * @param toParse line to parse for the first numerical digit
     * @return First numerical digit of a String, 0 if no digit is found
     */
    public static String findFirstNumericalDigitInString(String toParse) {
        Matcher matcher = Pattern
                .compile("\\d")
                .matcher(toParse);
        if(matcher.find()) {
            return matcher.group();
        }
        return "0";
    }

    /**
     * Method checks for first alphabetical digit in a String
     * @param toParse line to parse for the first alphabetical digit (lowercase)
     * @return First alphabetical digit of a String, 0 if no digit is found
     */
    public static Digit findFirstAlphabeticalDigitInString(String toParse) {
        List<Digit> allDigits = new ArrayList<>(Arrays.asList(Digit.values()));
        Digit result = Digit.ZERO;
        int index = Integer.MAX_VALUE;

        for(Digit d : allDigits) {
            Pattern pattern = Pattern.compile(Pattern.quote(d.getName()), Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(toParse);
            if(matcher.find() && index > toParse.indexOf(d.getName())) {
                index = toParse.indexOf(d.getName());
                result = d;
            }
        }
        return result;
    }

    /**
     * Method checks for last alphabetical digit in a String
     * @param toParse line to parse for the last alphabetical digit (lowercase)
     * @return last alphabetical digit of a String, 0 if no digit is found
     */
    public static Digit findLastAlphabeticalDigitInString(String toParse) {
        List<Digit> allDigits = new ArrayList<>(Arrays.asList(Digit.values()));
        Digit result = Digit.ZERO;
        int index = -1;

        for(Digit d : allDigits) {
            Pattern pattern = Pattern.compile(Pattern.quote(d.getName()), Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(toParse);
            if(matcher.find() && index < toParse.lastIndexOf(d.getName())) {
                index = toParse.lastIndexOf(d.getName());
                result = d;
            }
        }
        return result;
    }
}

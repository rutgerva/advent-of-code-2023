package com.rutgerva.aoc2023.utils;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static final String SPACE = " ";
    public static final String EMPTY_STRING = "";

    private StringUtils() {

    }


    public static List<Long> extractListOfNumbersFromString(String line) {
        Matcher matcher = getNumbersMatcher(line);

        return matcher.results()
                .map(MatchResult::group)
                .mapToLong(Long::parseLong)
                .boxed()
                .toList();
    }

    public static List<Integer> extractListOfIntegersFromString(String line) {
        Matcher matcher = getNumbersMatcher(line);

        return matcher.results()
                .map(MatchResult::group)
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
    }

    private static Matcher getNumbersMatcher(String line) {
        Pattern pattern = Pattern.compile("\\d+");
        return pattern.matcher(line);
    }

    public static List<Integer> extractListOfIntegersWithSignFromString(String line) {
        Matcher matcher = Pattern.compile("-?\\d+").matcher(line);

        return matcher.results()
                .map(MatchResult::group)
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
    }

    public static List<Long> extractListOfLongWithSignFromString(String line) {
        Matcher matcher = Pattern.compile("-?\\d+").matcher(line);

        return matcher.results()
                .map(MatchResult::group)
                .mapToLong(Long::parseLong)
                .boxed()
                .toList();
    }

    public static char[][] listToGrid(List<String> list) {
        return list.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    public static char[][] transposeArray(char[][] originalArray) {
        char[][] transposedArray = new char[originalArray[0].length][originalArray.length];
        for (int row = 0; row < originalArray.length; row++) {
            for (int column = 0; column < originalArray[0].length; column++) {
                transposedArray[column][row] = originalArray[row][column];
            }
        }
        return transposedArray;
    }
}

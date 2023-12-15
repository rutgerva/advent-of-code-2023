package com.rutgerva.aoc2023.utils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

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

    public static List<String> gridToList(char[][] grid) {
        return Arrays.stream(grid)
                .map(String::valueOf)
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

    /**
     * Determines if two strings are different by exactly 1 character
     *
     * @param one string one to compare to string two
     * @param two string two to compare to string one
     * @return true if string one and two are off by exactly 1 character else returns false.
     */
    public static boolean isOneOff(String one, String two) {
        if (one.length() != two.length())
            return false;
        long differences = IntStream.range(0, one.length())
                .mapToObj(i -> new int[]{one.charAt(i), two.charAt(i)})
                .filter(pair -> pair[0] != pair[1])
                .count();

        return differences == 1L;
    }

    public static int getOneOffIndex(String one, String two) {
        if (isOneOff(one, two)) {
            return IntStream.range(0, one.length())
                    .mapToObj(i -> new int[]{one.charAt(i), two.charAt(i), i})
                    .filter(pair -> pair[0] != pair[1]).iterator().next()[2];
        } else {
            throw new RuntimeException("Both strings have more or less than exactly one difference.");
        }
    }

    public static String generateRepetitiveStringOfSymbols(int amount, char symbol) {
        return String.valueOf(symbol).repeat(Math.max(0, amount));
    }
}

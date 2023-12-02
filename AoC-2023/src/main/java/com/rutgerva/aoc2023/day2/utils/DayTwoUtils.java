package com.rutgerva.aoc2023.day2.utils;

import java.util.Arrays;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayTwoUtils {


    public static final Integer MAX_RED_CUBES = 12;
    public static final Integer MAX_BLUE_CUBES = 14;
    public static final Integer MAX_GREEN_CUBES = 13;

    public static final String BLUE = "blue";
    public static final String RED = "red";
    public static final String GREEN = "green";

    private static final String EMPTY = "";

    private static Integer sumOfCubesShownInSet(String color, String set) {
        String[] matchesArray = extractCubesFromString(color, set);

        return Arrays.stream(matchesArray)
                .map((s -> s.replaceAll("[^0-9]", EMPTY)))
                .mapToInt(Integer::parseInt)
                .sum();
    }

    private static Integer minimumRequiredAmountOfCubesInGame(String color, String gameLine) {
        String[] cubesPerSet = extractCubesFromString(color, gameLine);
        return Arrays.stream(cubesPerSet)
                .map((s -> s.replaceAll("[^0-9]", EMPTY)))
                .mapToInt(Integer::parseInt)
                .max().orElse(0);
    }

    public static Integer getPowerOfGame(String gameLine) {
        Integer redCubes = 0, blueCubes = 0, greenCubes = 0;

        redCubes = minimumRequiredAmountOfCubesInGame(DayTwoUtils.RED, gameLine);
        blueCubes = minimumRequiredAmountOfCubesInGame(DayTwoUtils.BLUE, gameLine);
        greenCubes = minimumRequiredAmountOfCubesInGame(DayTwoUtils.GREEN, gameLine);

        return redCubes * blueCubes * greenCubes;
    }

    public static Boolean isValidSet(String set) {
        Integer redCubes = 0, blueCubes = 0, greenCubes = 0;

        redCubes = sumOfCubesShownInSet(DayTwoUtils.RED, set);
        blueCubes = sumOfCubesShownInSet(DayTwoUtils.BLUE, set);
        greenCubes = sumOfCubesShownInSet(DayTwoUtils.GREEN, set);

        return redCubes <= DayTwoUtils.MAX_RED_CUBES
                && blueCubes <= DayTwoUtils.MAX_BLUE_CUBES
                && greenCubes <= DayTwoUtils.MAX_GREEN_CUBES;
    }

    private static String[] extractCubesFromString(String color, String toExtract) {
        Pattern pattern = Pattern.compile(String.format("\\b(\\d+ %s)\\b", color));
        Matcher matcher = pattern.matcher(toExtract);
        return matcher.results()
                .map(MatchResult::group)
                .toArray(String[]::new);
    }
}

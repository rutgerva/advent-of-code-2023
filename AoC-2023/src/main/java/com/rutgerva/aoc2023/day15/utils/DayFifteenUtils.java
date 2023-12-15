package com.rutgerva.aoc2023.day15.utils;

import java.util.List;
import java.util.stream.IntStream;

public final class DayFifteenUtils {

    private DayFifteenUtils() {
    }

    public static int hashString(String toHash) {
        Integer hashValue = 0;
        List<Integer> asciiValues = toHash.chars()
                .mapToObj(Integer::valueOf)
                .toList();
        for (Integer asciiValue : asciiValues) {
            hashValue += asciiValue;
            hashValue *= 17;
            hashValue = hashValue % 256;
        }
        return hashValue;
    }
}

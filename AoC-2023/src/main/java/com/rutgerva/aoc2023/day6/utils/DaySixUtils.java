package com.rutgerva.aoc2023.day6.utils;

import com.rutgerva.aoc2023.day6.models.Race;
import com.rutgerva.aoc2023.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public final class DaySixUtils {
    private static List<Race> races;

    private DaySixUtils() {
    }

    public static List<String> cleanInputFromSpaces(List<String> toClean) {
        return toClean.stream()
                .map(s -> s.replaceAll(StringUtils.SPACE, StringUtils.EMPTY_STRING))
                .toList();
    }

    public static void initializeRaces(List<String> input) throws RuntimeException {
        races = new ArrayList<>();
        List<Long> times = StringUtils.extractListOfNumbersFromString(input.get(0));
        List<Long> distances = StringUtils.extractListOfNumbersFromString(input.get(1));
        if (times.size() == distances.size()) {
            for (int i = 0; i < times.size(); ++i) {
                races.add(new Race(times.get(i), distances.get(i)));
            }
        } else {
            throw new RuntimeException("Mismatch in races definitions");
        }
    }

    public static Long processRaces() {
        long result = 1L;
        for (Race race : races) {
            long winningCombinations = race.winnableCombinations();
            result *= winningCombinations;
        }
        return result;
    }
}

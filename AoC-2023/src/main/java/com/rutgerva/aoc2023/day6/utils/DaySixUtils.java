package com.rutgerva.aoc2023.day6.utils;

import com.rutgerva.aoc2023.utils.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DaySixUtils {
    public static List<String> input = new ArrayList<>();
    public static List<Race> races;

    public static void readInput(String inputFile) throws IOException {
        //Read file
        input = Files.readAllLines(Paths.get("src/main/resources/" + inputFile));
    }

    public static void cleanInputFromSpaces() {
        input = input.stream()
                .map(s -> s.replaceAll(" ", ""))
                .toList();
    }

    public static void initializeRaces() throws RuntimeException {
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
}

package com.rutgerva.aoc2023.day3.utils;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayThreeUtils {

    private static List<ImmutablePair<Integer, Integer>> indexesOfSymbols;
    //K = Coordinate of Gear, V = ratio
    public static List<GearRatio> gearRatios;
    public static List<PartNumber> partNumbers;
    public static List<String> engineAsList = new ArrayList<>();
    private static Integer maxX = 0;
    private  static Integer maxY = 0;
    private static final String GEAR_SYMBOL = "*";

    public static void initializePuzzleSolver(String inputFile) throws IOException {
        //Create lists + maps
        indexesOfSymbols = new ArrayList<>();
        partNumbers = new ArrayList<>();
        engineAsList = new ArrayList<>();
        gearRatios = new ArrayList<>();
        //Read file
        engineAsList = Files.readAllLines(Paths.get("src/main/resources/" + inputFile));
        if (!engineAsList.isEmpty()) {
            maxX = engineAsList.size() - 1;
            maxY = engineAsList.get(0).length() - 1;
            extractAllSymbolIndexPairs();
            extractAllPartNumbers();
        }
    }

    private static void extractAllSymbolIndexPairs() {
        int x = 0;
        for (String s : engineAsList) {
            Pattern pattern = Pattern.compile("[^.\\d]");
            Matcher matcher = pattern.matcher(s);
            int processedIndex = 0;
            while (matcher.find()) {
                String symbol = matcher.group();
                ImmutablePair<Integer, Integer> index = new ImmutablePair<>(x, s.indexOf(symbol, processedIndex));
                indexesOfSymbols.add(index);
                if (GEAR_SYMBOL.equals(symbol)) {
                    gearRatios.add(new GearRatio(x, s.indexOf(symbol, processedIndex), 0));
                }
                processedIndex = s.indexOf(matcher.group(), processedIndex) + 1;
            }
            x++;
        }
    }

    private static void extractAllPartNumbers() {
        int row = 0;
        for (String s : engineAsList) {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(s);
            int processedIndex = 0;
            while (matcher.find()) {
                String number = matcher.group();
                int begin = s.indexOf(number, processedIndex);
                int end = begin + number.length() - 1;
                partNumbers.add(new PartNumber(row, begin, end, Integer.valueOf(number)));
                processedIndex = end + 1;
            }
            row++;
        }
    }

    private static Boolean hasSymbol(ImmutablePair<Integer, Integer> toCheck) {
        return indexesOfSymbols.contains(toCheck);
    }

    public static Boolean checkNoAdjacentSymbols(int row, int begin, int end) {
        for (int x = row - 1; x <= row + 1; ++x) {
            for (int y = begin - 1; y <= end + 1; y++) {
                if (TRUE.equals(isAdjacentField(x, y, row, begin, end))
                        && TRUE.equals(DayThreeUtils.hasSymbol(new ImmutablePair<>(x, y)))) {
                    return FALSE;
                }
            }
        }
        return TRUE;
    }

    public static boolean isAdjacentField(int x, int y, int numberRow, int numberBegin, int numberEnd) {
        //if within bounds
        if (x >= 0 && x <= maxX && y >= 0 && y <= maxY) {
            return (x == numberRow - 1 && (y >= numberBegin - 1 && y <= numberEnd + 1)) ||
                    (x == numberRow && (y == numberBegin - 1 || y == numberEnd + 1)) ||
                    (x == numberRow + 1 && (y >= numberBegin - 1 && y <= numberEnd + 1));
        }
        return FALSE;
    }

}

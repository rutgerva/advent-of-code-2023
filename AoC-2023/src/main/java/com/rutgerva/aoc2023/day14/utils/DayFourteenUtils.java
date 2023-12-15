package com.rutgerva.aoc2023.day14.utils;

import com.rutgerva.aoc2023.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class DayFourteenUtils {

    private DayFourteenUtils() {
    }

    public static List<String> cycleList(List<String> beforeCycle) {
        return rollEast(rollSouth(rollWest(rollNorth(beforeCycle))));
    }

    public static List<String> rollNorth(List<String> properlyOrientedList) {
        char[][] transposedGrid = StringUtils.transposeArray(StringUtils.listToGrid(properlyOrientedList));
        List<String> transposedGridList = StringUtils.gridToList(transposedGrid);
        return StringUtils.gridToList(StringUtils.transposeArray(StringUtils.listToGrid(tilt(transposedGridList))));
    }

    public static List<String> rollWest(List<String> properlyOrientedList) {
        return tilt(properlyOrientedList);
    }

    public static List<String> rollSouth(List<String> properlyOrientedList) {
        char[][] transposedGrid = StringUtils.transposeArray(StringUtils.listToGrid(properlyOrientedList));
        List<String> transposedGridList = StringUtils.gridToList(transposedGrid);
        List<String> southOrientedList = reverseStringsInList(transposedGridList);
        return StringUtils.gridToList(StringUtils.transposeArray(StringUtils.listToGrid(reverseStringsInList(tilt(southOrientedList)))));
    }

    public static List<String> rollEast(List<String> properlyOrientedList) {
        List<String> westOrientedList = reverseStringsInList(properlyOrientedList);
        return reverseStringsInList(tilt(westOrientedList));
    }

    private static List<String> tilt(List<String> grid) {

        List<String> gridAfterPull = new ArrayList<>();
        for (String line : grid) {
            List<Integer> indicesOfSolidRocks = new ArrayList<>(indicesOfCubeShapedRocks(line));
            if (!indicesOfSolidRocks.contains(line.length() - 1))
                indicesOfSolidRocks.add(line.length() - 1); // add index of last index in our line in case no rock is there (the invisible rock =))
            int lastUsedIndex = 0;
            StringBuilder afterPull = new StringBuilder();
            for (Integer index : indicesOfSolidRocks) {
                String part = line.substring(lastUsedIndex, index + 1);
                int amountOfRoundedRocks = amountOfRoundRocks(part);
                int padding = part.length() - amountOfRoundedRocks - 1;
                afterPull.append(StringUtils.generateRepetitiveStringOfSymbols(amountOfRoundedRocks, 'O'))
                        .append(StringUtils.generateRepetitiveStringOfSymbols(padding, '.'))
                        .append(line.charAt(index) == '#' ? '#' : '.');
                lastUsedIndex = index + 1;
            }
            gridAfterPull.add(afterPull.substring(0, line.length()));
        }

        return gridAfterPull;
    }

    private static List<Integer> indicesOfCubeShapedRocks(String line) {
        return IntStream.range(0, line.length())
                .filter(i -> line.charAt(i) == '#')
                .boxed().toList();
    }

    public static int amountOfRoundRocks(String line) {
        return (int) IntStream.range(0, line.length())
                .filter(i -> line.charAt(i) == 'O')
                .count();
    }

    private static List<String> reverseStringsInList(List<String> toReverse) {
        return toReverse.stream()
                .map(s -> new StringBuilder(s).reverse().toString())
                .toList();
    }
}

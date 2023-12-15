package com.rutgerva.aoc2023.day14.utils;

import com.rutgerva.aoc2023.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class DayFourteenUtils {

    private DayFourteenUtils() {
    }

    public static List<String> pullLever(List<String> grid) {

        char[][] transposedGrid = StringUtils.transposeArray(StringUtils.listToGrid(grid));
        List<String> transposedGridList = StringUtils.gridToList(transposedGrid);

        List<String> gridAfterPull = new ArrayList<>();
        for (String line : transposedGridList) {
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
            gridAfterPull.add(afterPull.toString());
        }
        gridAfterPull = StringUtils.gridToList(StringUtils.transposeArray(StringUtils.listToGrid(gridAfterPull)));
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
}

package com.rutgerva.aoc2023.day13.utils;

import com.rutgerva.aoc2023.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public final class DayThirteenUtils {

    private DayThirteenUtils() {
    }

    public static int findPerfectReflectionHorizontal(List<String> grid, boolean allowOneOff) {
        return findReflectionLineInGrid(grid, allowOneOff);
    }

    public static int findPerfectReflectionVertical(List<String> grid, boolean allowOneOff) {
        char[][] transposedGrid = StringUtils.transposeArray(StringUtils.listToGrid(grid));
        List<String> transpose = Arrays.stream(transposedGrid)
                .map(String::valueOf)
                .toList();
        return findReflectionLineInGrid(transpose, allowOneOff);
    }

    public static int findPerfectReflectionHorizontal(List<String> grid) {
        return findReflectionLineInGrid(grid);
    }

    public static int findPerfectReflectionVertical(List<String> grid) {
        char[][] transposedGrid = StringUtils.transposeArray(StringUtils.listToGrid(grid));
        List<String> transpose = Arrays.stream(transposedGrid)
                .map(String::valueOf)
                .toList();
        return findReflectionLineInGrid(transpose);
    }

    private static int findReflectionLineInGrid(List<String> grid) {
        return findReflectionLineInGrid(grid, false);
    }

    private static int findReflectionLineInGrid(List<String> grid, boolean allowOneOff) {
        for (int checkedLine = 1; checkedLine < grid.size(); checkedLine++) {
            boolean ended = false;
            boolean isReflection = true;
            boolean skippedOneOff = false;
            int checkLeft = checkedLine - 1;
            int checkRight = checkedLine;
            while (isReflection && !ended) {
                String left = grid.get(checkLeft);
                String right = grid.get(checkRight);
                //when one off's are permitted once, skip this loop and check the next pair
                if (allowOneOff && StringUtils.isOneOff(left, right) && !skippedOneOff) {
                    skippedOneOff = true;
                } else {
                    isReflection = grid.get(checkLeft).equals(grid.get(checkRight));
                }
                checkLeft -= 1;
                checkRight += 1;
                if (checkLeft < 0 || checkRight == grid.size())
                    ended = true;
            }
            //in this case we've found the original reflection line but we don't want this one to return since we're looking for the new one
            if (allowOneOff && !skippedOneOff && isReflection)
                isReflection = false;
            if (isReflection)
                return checkedLine;
        }
        return 0;
    }

    public static List<List<String>> initialize(List<String> inputContent) {
        List<List<String>> listOfGrids = new ArrayList<>();
        List<String> grid = new ArrayList<>();
        for (String s : inputContent) {
            if (s.equals(StringUtils.EMPTY_STRING)) {
                listOfGrids.add(grid);
                grid = new ArrayList<>();
            } else {
                grid.add(s);
            }
        }
        //add last processed grid to list
        listOfGrids.add(grid);
        return listOfGrids;
    }
}

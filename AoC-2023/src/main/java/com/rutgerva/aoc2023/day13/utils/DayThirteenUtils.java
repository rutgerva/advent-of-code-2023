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
        for (int checkedLine = 1; checkedLine < grid.size(); checkedLine++) {
            boolean ended = false;
            boolean isReflection = true;
            int checkLeft = checkedLine - 1;
            int checkRight = checkedLine;
            while (isReflection && !ended) {
                isReflection = grid.get(checkLeft).equals(grid.get(checkRight));
                checkLeft -= 1;
                checkRight += 1;
                if (checkLeft < 0 || checkRight == grid.size())
                    ended = true;
            }
            if (isReflection)
                return checkedLine;
        }
        return 0;
    }

//    private static List<Integer[]> getAllPairsOfPossibleReflections(List<String> input) {
//        int row = 0;
//        List<Integer[]> pairs = new ArrayList<>();
//        for (String line : input) {
//            Pattern pattern = Pattern.compile("\\Q..\\E|\\Q##\\E");
//            Matcher matcher = pattern.matcher(line);
//            int fromIndex = 0;
//            while (matcher.find()) {
//                String match = matcher.group();
//                fromIndex = line.indexOf(match, fromIndex);
//                log.debug("Row " + row + " Potential perfect vertical reflection: " + match + " found at index " + fromIndex);
//                if (isReflection(fromIndex + 1, line))
//                    pairs.add(new Integer[]{row, fromIndex});
//                fromIndex += 1;
//            }
//            final int rowCheck = row;
//            if (pairs.stream().filter(pair -> pair[0].equals(rowCheck)).toList().isEmpty()) //no pairs added means there cannot be a perfect reflection
//                return new ArrayList<>();
//            row++;
//        }
//        return pairs;
//    }

//    private static int findCommonValue(List<Integer[]> pairs) {
//        if (pairs.isEmpty())
//            return -1;
//        Set<Integer> commonIndices = pairs.stream()
//                .collect(Collectors.groupingBy(pair -> pair[0],
//                        Collectors.mapping(pair -> pair[1], Collectors.toSet())))
//                .values().stream()
//                .reduce((set1, set2) -> {
//                    set1.retainAll(set2);
//                    return set1;
//                }).orElseThrow();
//        return commonIndices.isEmpty() ? -1 : commonIndices.iterator().next();
//    }

//    private static boolean isReflection(int reflectionIndex, String line) {
//        String leftPart;
//        String rightPart;
//        if (reflectionIndex > line.length() / 2) {
//            rightPart = line.substring(reflectionIndex);
//            leftPart = line.substring(reflectionIndex - rightPart.length(), reflectionIndex);
//        } else {
//            leftPart = line.substring(0, reflectionIndex);
//            rightPart = line.substring(reflectionIndex, reflectionIndex + leftPart.length() - 1);
//        }
//        leftPart = new StringBuilder(leftPart).reverse().toString();
//        return leftPart.equals(rightPart);
//    }

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

package com.rutgerva.aoc2023.day11.utils;

import com.rutgerva.aoc2023.day11.models.DataPoint;
import com.rutgerva.aoc2023.utils.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public final class DayElevenUtils {
    public static final Character EMPTY_SPACE_SYMBOL = '.';
    public static final Character GALAXY_SYMBOL = '#';
    private static List<ImmutablePair<DataPoint, DataPoint>> galaxyPairs;
    private static List<Integer> emptyRowsIndices;
    private static List<Integer> emptyColumnIndices;

    private DayElevenUtils() {
    }

    public static void findEmptyRows(List<String> input) {
        emptyRowsIndices = new ArrayList<>(IntStream.range(0, input.size() - 1)
                .filter(i -> StringUtils.onlyContainsChar('.', input.get(i)))
                .boxed()
                .toList());
    }

    public static void findEmptyColumns(List<String> input) {
        List<String> transposedGrid = StringUtils.gridToList(StringUtils.transposeArray(StringUtils.listToGrid(input)));
        emptyColumnIndices = new ArrayList<>(IntStream.range(0, input.size() - 1)
                .filter(i -> StringUtils.onlyContainsChar('.', transposedGrid.get(i)))
                .boxed()
                .toList());
    }

    public static Long process(List<String> input, int expansionFactor) {

        char[][] galaxyGrid = StringUtils.listToGrid(input);
        List<DataPoint> galaxies = new ArrayList<>();
        int galaxiesFound = 0;
        for (int row = 0; row < galaxyGrid.length; row++) {
            for (int column = 0; column < galaxyGrid[0].length; column++) {
                if (galaxyGrid[row][column] == GALAXY_SYMBOL) {
                    galaxies.add(new DataPoint(++galaxiesFound, row, column));
                }
            }
        }
        makeGalaxyPairs(galaxies);
        Long result = 0L;
        for (ImmutablePair<DataPoint, DataPoint> pair : galaxyPairs) {
            result += findShortestPath(pair.left, pair.right, input);
            long rowExpansions = emptyRowsIndices.stream()
                    .filter(i -> isBetweenRowsOfPair(i, pair.left, pair.right))
                    .mapToInt(Integer::valueOf)
                    .count();

            long columnExpansion = emptyColumnIndices.stream()
                    .filter(i -> isBetweenColumnsOfPair(i, pair.left, pair.right))
                    .mapToInt(Integer::valueOf)
                    .count();
            result += (rowExpansions * expansionFactor) + (columnExpansion * expansionFactor);
        }
        return result;
    }

    private static void makeGalaxyPairs(List<DataPoint> allGalaxies) {
        galaxyPairs = new ArrayList<>();
        while (!allGalaxies.isEmpty()) {
            DataPoint d1 = allGalaxies.remove(0);
            for (DataPoint galaxy : allGalaxies) {
                galaxyPairs.add(new ImmutablePair<>(d1, galaxy));
            }
        }
    }

    private static boolean isBetweenRowsOfPair(int index, DataPoint point1, DataPoint point2) {
        return (point1.getXCoordinate() < index && index < point2.getXCoordinate()) ||
                (point2.getXCoordinate() < index && index < point1.getXCoordinate());
    }

    private static boolean isBetweenColumnsOfPair(int index, DataPoint point1, DataPoint point2) {
        return (point1.getYCoordinate() < index && index < point2.getYCoordinate()) ||
                (point2.getYCoordinate() < index && index < point1.getYCoordinate());
    }

    private static Integer findShortestPath(DataPoint pointA, DataPoint pointB, List<String> spaceMap) {
        char[][] spaceMapGrid = StringUtils.listToGrid(spaceMap);
        int rows = spaceMapGrid.length;
        int columns = spaceMapGrid[0].length;

        boolean[][] visited = new boolean[rows][columns];
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{pointA.getXCoordinate(), pointA.getYCoordinate(), 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int x = current[0];
            int y = current[1];
            int distance = current[2];

            if (x == pointB.getXCoordinate() && y == pointB.getYCoordinate())
                return distance;

            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];

                if (isValid(newX, newY, rows, columns) && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.offer(new int[]{newX, newY, distance + 1});
                }
            }
        }
        return -1; //Not found
    }

    private static boolean isValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

}

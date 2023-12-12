package com.rutgerva.aoc2023.day11.utils;

import com.rutgerva.aoc2023.day11.models.DataPoint;
import com.rutgerva.aoc2023.utils.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public final class DayElevenUtils {
    public static final Character EMPTY_SPACE_SYMBOL = '.';
    public static final Character GALAXY_SYMBOL = '#';
    private static List<ImmutablePair<DataPoint, DataPoint>> galaxyPairs;

    private DayElevenUtils() {
    }

    public static Integer process(List<String> input) {
        input = expandGalaxy(input);
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
        Integer result = 0;
        for (ImmutablePair<DataPoint, DataPoint> pair : galaxyPairs) {
            result += findShortestPath(pair.left, pair.right, input);
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

    private static List<String> expandGalaxy(List<String> input) {
        List<String> tmp = expandRows(input);
        return expandColumns(tmp);
    }

    private static List<String> expandColumns(List<String> input) {
        char[][] galaxyGrid = StringUtils.listToGrid(input);
        List<String> tmp = new ArrayList<>(input); //immutability issues
        int columnsAdded = 0; // needed to take the already expanded grid into account everytime we add a column
        for (int column = 0; column < galaxyGrid[0].length; column++) {
            boolean noGalaxiesFound = true;
            for (char[] chars : galaxyGrid) {
                if (chars[column] == '#') {
                    noGalaxiesFound = false;
                    break;
                }
            }
            if (noGalaxiesFound) {
                final int indexToAdd = column + columnsAdded++;
                tmp = tmp.stream()
                        .map(line -> line.substring(0, indexToAdd) + EMPTY_SPACE_SYMBOL + line.substring(indexToAdd))
                        .toList();
            }
        }
        return tmp;
    }

    private static List<String> expandRows(List<String> input) {
        char[][] galaxyGrid = StringUtils.listToGrid(input);
        int rowsAdded = 0; // needed to take the already expanded grid into account everytime we add a column
        for (int row = 0; row < galaxyGrid.length; row++) {
            boolean noGalaxiesFound = true;
            for (int column = 0; column < galaxyGrid[0].length; column++) {
                if (galaxyGrid[row][column] == '#') {
                    noGalaxiesFound = false;
                    break;
                }
            }
            if (noGalaxiesFound) {
                input.add(row + rowsAdded++, createEmptyRow(galaxyGrid.length));
            }
        }
        return input;
    }

    private static String createEmptyRow(Integer amountOfSpaces) {
        return String.valueOf(EMPTY_SPACE_SYMBOL).repeat(Math.max(0, amountOfSpaces));
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

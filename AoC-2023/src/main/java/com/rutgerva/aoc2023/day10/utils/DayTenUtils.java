package com.rutgerva.aoc2023.day10.utils;

import com.rutgerva.aoc2023.day10.models.Tile;
import com.rutgerva.aoc2023.day10.models.TileSymbol;

import java.util.ArrayList;
import java.util.List;

public final class DayTenUtils {

    private static List<Tile> maze;
    private static Tile startTile;
    private static Integer maxRows;

    private DayTenUtils() {
    }

    public static void initialize(List<String> input) {
        maze = new ArrayList<>();
        int row = 0;
        for (String line : input) {
            for (int column = 0; column < line.length(); column++) {
                Tile tile = new Tile(TileSymbol.getTile(line.charAt(column)),
                        row, column);
                maze.add(tile);
                if (line.charAt(column) == 'S')
                    startTile = tile;
            }
            row++;
        }
        maxRows = row;
    }

    public static Integer findMaximumDistance() {
        Integer distance = 1;
        boolean reachedSameField = false;
        //find 2 positions that have startPos in their connecting positions.
        List<Tile> pathWays = findTwoPathwayStarts();
        Tile previousTileOne = startTile;
        Tile previousTileTwo = startTile;
        Tile currentTileOne = pathWays.get(0);
        Tile currentTileTwo = pathWays.get(1);
        while (!reachedSameField) {
            Tile tmp1 = currentTileOne;
            Tile tmp2 = currentTileTwo;
            currentTileOne = moveToNextTile(currentTileOne, previousTileOne);
            currentTileTwo = moveToNextTile(currentTileTwo, previousTileTwo);
            previousTileOne = tmp1;
            previousTileTwo = tmp2;
            if (currentTileOne.equals(currentTileTwo)) {
                reachedSameField = true;
            }
            distance++;
        }
        return distance;
    }

    private static Tile moveToNextTile(Tile currentTile, Tile previousTile) {
        Integer[][] connector = currentTile.connects();
        Tile connectorOne = findTileBasedOnCoordinates(connector[0][0], connector[0][1]);
        Tile connectorTwo = findTileBasedOnCoordinates(connector[1][0], connector[1][1]);
        return connectorOne.equals(previousTile) ? connectorTwo : connectorOne;
    }

    public static List<Tile> findTwoPathwayStarts() {
        List<Tile> pathWays = new ArrayList<>();
        for (int i = startTile.getXCoordinate() - 1; i <= startTile.getXCoordinate() + 1; i++) {
            for (int j = startTile.getYCoordinate() - 1; j <= startTile.getYCoordinate() + 1; j++) {
                Tile connectingTile = findTileBasedOnCoordinates(i, j);
                //out of bounds preventions
                if (connectingTile != null) {
                    Integer[][] connector = connectingTile.connects();
                    Tile connectorOne = findTileBasedOnCoordinates(connector[0][0], connector[0][1]);
                    Tile connectorTwo = findTileBasedOnCoordinates(connector[1][0], connector[1][1]);
                    if (connectorOne != null && connectorOne.equals(startTile))
                        pathWays.add(connectingTile);
                    else if (connectorTwo != null && connectorTwo.equals(startTile)) {
                        pathWays.add(connectingTile);
                    }
                }
            }
        }
        return pathWays;
    }

    private static Tile findTileBasedOnCoordinates(Integer x, Integer y) {
        return maze.stream()
                .filter((tile -> tile.getXCoordinate().equals(x)
                        && tile.getYCoordinate().equals(y)))
                .findFirst()
                .orElse(null);
    }
}

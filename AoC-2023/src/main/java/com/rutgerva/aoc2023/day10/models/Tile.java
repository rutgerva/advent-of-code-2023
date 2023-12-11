package com.rutgerva.aoc2023.day10.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Tile {
    private final TileSymbol symbol;
    private final Integer xCoordinate;
    private final Integer yCoordinate;


    public Integer[][] connects() {
        Integer[][] connectors = new Integer[2][];
        switch (this.symbol) {
            //UP and DOWN
            case VERTICAL -> {
                connectors[0] = new Integer[]{xCoordinate + 1, yCoordinate};
                connectors[1] = new Integer[]{xCoordinate - 1, yCoordinate};

            }
            //RIGHT and LEFT
            case HORIZONTAL -> {
                connectors[0] = new Integer[]{xCoordinate, yCoordinate + 1};
                connectors[1] = new Integer[]{xCoordinate, yCoordinate - 1};
            }
            //DOWN  and RIGHT
            case NINETY_DEGREE_NORTH_EAST -> {
                connectors[0] = new Integer[]{xCoordinate, yCoordinate + 1};
                connectors[1] = new Integer[]{xCoordinate - 1, yCoordinate};
            }
            // DOWN and LEFT
            case NINETY_DEGREE_NORTH_WEST -> {
                connectors[0] = new Integer[]{xCoordinate, yCoordinate - 1};
                connectors[1] = new Integer[]{xCoordinate - 1, yCoordinate};
            }
            //UP and RIGHT
            case NINETY_DEGREE_SOUTH_EAST -> {
                connectors[0] = new Integer[]{xCoordinate + 1, yCoordinate};
                connectors[1] = new Integer[]{xCoordinate, yCoordinate + 1};
            }
            //UP and LEFT
            case NINETY_DEGREE_SOUTH_WEST -> {
                connectors[0] = new Integer[]{xCoordinate + 1, yCoordinate};
                connectors[1] = new Integer[]{xCoordinate, yCoordinate - 1};
            }
            default -> {
                connectors[0] = new Integer[]{-1, -1};
                connectors[1] = new Integer[]{-1, -1};
            }
        }
        return connectors;
    }
}

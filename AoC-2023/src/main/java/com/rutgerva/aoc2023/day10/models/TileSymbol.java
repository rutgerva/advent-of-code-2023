package com.rutgerva.aoc2023.day10.models;


import lombok.Getter;

public enum TileSymbol {
    VERTICAL('|'),
    HORIZONTAL('-'),
    NINETY_DEGREE_NORTH_EAST('L'),
    NINETY_DEGREE_NORTH_WEST('J'),
    NINETY_DEGREE_SOUTH_WEST('7'),
    NINETY_DEGREE_SOUTH_EAST('F'),
    START('S'),
    SOIL('.');

    TileSymbol(Character symbol) {
        this.symbol = symbol;
        this.isStart = symbol.equals('S');
    }

    @Getter
    private Character symbol;
    @Getter
    private boolean isStart;

    public static TileSymbol getTile(Character symbol) {
        return switch (symbol) {
            case '|' -> VERTICAL;
            case '-' -> HORIZONTAL;
            case 'L' -> NINETY_DEGREE_NORTH_EAST;
            case 'J' -> NINETY_DEGREE_NORTH_WEST;
            case '7' -> NINETY_DEGREE_SOUTH_WEST;
            case 'F' -> NINETY_DEGREE_SOUTH_EAST;
            case 'S' -> START;
            case '.' -> SOIL;
            default -> null;
        };
    }
}

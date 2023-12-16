package com.rutgerva.aoc2023.day16.enums;

public enum Tile {
    EMPTY_SPACE('.'),
    HSPLIT('-'),
    VSPLIT('|'),
    MIRROR_TILT_RIGHT('/'),
    MIRROR_TILT_LEFT('\\'),
    INVALID(' ');

    private final char symbol;

    Tile(char symbol) {
        this.symbol = symbol;
    }

    public static Tile getTileBySymbol(char symbol) {
        return switch (symbol) {
            case '.' -> EMPTY_SPACE;
            case '-' -> HSPLIT;
            case '/' -> MIRROR_TILT_RIGHT;
            case '\\' -> MIRROR_TILT_LEFT;
            case '|' -> VSPLIT;
            default -> INVALID;
        };
    }
}

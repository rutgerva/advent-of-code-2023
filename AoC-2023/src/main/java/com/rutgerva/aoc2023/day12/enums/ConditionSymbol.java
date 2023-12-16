package com.rutgerva.aoc2023.day12.enums;

public enum ConditionSymbol {
    OPERATIONAL('.'),
    BROKEN('#'),
    UNKNOWN('?');

    private final char symbol;

    ConditionSymbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean equals(char c) {
        return c == symbol;
    }
}

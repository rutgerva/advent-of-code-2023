package com.rutgerva.aoc2023.day16.enums;

public enum TravellingDirection {
    UP(new int[]{-1, 0}),
    DOWN(new int[]{1, 0}),
    LEFT(new int[]{0, -1}),
    RIGHT(new int[]{0, 1});

    private int[] movement;

    TravellingDirection(int[] movement) {
        this.movement = movement;
    }

    public int[] getMovement() {
        return this.movement;
    }
}

package com.rutgerva.aoc2023.day6.models;

public record Race(Long duration, Long distance) {
    public Long winnableCombinations() {
        long minimumSecondsNeededToWin = calculateMinimumSecondsNeededToWin();
        long maxSecondsAllowedToWin = calculateMaximumSecondsAllowedToWin();

        return maxSecondsAllowedToWin - minimumSecondsNeededToWin + 1;
    }

    private Long calculateMinimumSecondsNeededToWin() {
        for (long i = 1; i < distance; ++i) {
            long racingSeconds = duration - i;
            long distanceTraveled = racingSeconds * i;
            if (distanceTraveled > distance) {
                return i;
            }
        }
        return Long.MAX_VALUE;
    }

    private Long calculateMaximumSecondsAllowedToWin() {

        for (long i = duration; i >= 0; i--) {
            long racingSeconds = duration - i;
            long distanceTraveled = racingSeconds * i;
            if (distanceTraveled > distance) {
                return i;
            }
        }
        return Long.MAX_VALUE;
    }
}

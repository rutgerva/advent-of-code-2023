package com.rutgerva.aoc2023.day6;

import com.rutgerva.aoc2023.PuzzleSolver;
import static com.rutgerva.aoc2023.day6.utils.DaySixUtils.initializeRaces;
import static com.rutgerva.aoc2023.day6.utils.DaySixUtils.races;
import static com.rutgerva.aoc2023.day6.utils.DaySixUtils.readInput;
import com.rutgerva.aoc2023.day6.utils.Race;

import java.io.IOException;

public class PuzzleOneSolver extends PuzzleSolver {

    public PuzzleOneSolver(String inputFile) {
        super(inputFile);
    }

    public Long solve() {
        try {
            Long result = 1L;
            readInput(inputFile);
            initializeRaces();
            for (Race race : races) {
                long winningCombinations = race.winnableCombinations();
                result *= winningCombinations;
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}

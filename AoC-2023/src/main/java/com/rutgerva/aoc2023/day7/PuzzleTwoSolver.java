package com.rutgerva.aoc2023.day7;

import com.rutgerva.aoc2023.PuzzleSolver;
import static com.rutgerva.aoc2023.day6.utils.DaySixUtils.readInput;

import java.io.IOException;

public class PuzzleTwoSolver extends PuzzleSolver {

    public PuzzleTwoSolver(String inputFile) {
        super(inputFile);
    }

    public Long solve() {
        try {
            Long result = 1L;
            readInput(inputFile);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}

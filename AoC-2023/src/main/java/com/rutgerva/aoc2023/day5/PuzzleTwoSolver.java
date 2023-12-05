package com.rutgerva.aoc2023.day5;

import com.rutgerva.aoc2023.PuzzleSolver;
import static com.rutgerva.aoc2023.day4.utils.DayFourUtils.readInput;

import java.io.IOException;

public class PuzzleTwoSolver extends PuzzleSolver {

    public PuzzleTwoSolver(String inputFile) {
        super(inputFile);
    }

    public Integer solve() {
        try {
            readInput(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

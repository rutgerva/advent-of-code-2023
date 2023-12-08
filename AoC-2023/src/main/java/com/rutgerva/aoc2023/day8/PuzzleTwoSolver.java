package com.rutgerva.aoc2023.day8;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.utils.ReaderUtils;

import java.io.IOException;

public class PuzzleTwoSolver extends PuzzleSolver {
    private static Long result = Long.MAX_VALUE;

    public PuzzleTwoSolver(String inputFile) {
        super(inputFile);
    }

    public Long solve() {
        try {
            inputContent = ReaderUtils.readInputFile(inputFile);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}

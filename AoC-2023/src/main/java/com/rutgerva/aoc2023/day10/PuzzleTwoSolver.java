package com.rutgerva.aoc2023.day10;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day10.utils.DayTenUtils;
import com.rutgerva.aoc2023.utils.ReaderUtils;

import java.io.IOException;

public class PuzzleTwoSolver extends PuzzleSolver {

    public PuzzleTwoSolver(String inputFile) {
        super(inputFile);
    }

    public Long solve() {
        try {
            inputContent = ReaderUtils.readInputFile(inputFile);
            DayTenUtils.initialize(inputContent);
            return 0L;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}

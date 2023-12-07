package com.rutgerva.aoc2023.day7;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day7.utils.DaySevenUtils;
import com.rutgerva.aoc2023.utils.ReaderUtils;

import java.io.IOException;

public class PuzzleTwoSolver extends PuzzleSolver {
    public PuzzleTwoSolver(String inputFile) {
        super(inputFile);
    }

    public Integer solve() {
        try {
            inputContent = ReaderUtils.readInputFile(inputFile);
            DaySevenUtils.initialize(inputContent, "J23456789TQKA");
            DaySevenUtils.promoteHands();
            return DaySevenUtils.processHands();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

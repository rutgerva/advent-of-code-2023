package com.rutgerva.aoc2023.day8;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day8.utils.DayEightUtils;
import com.rutgerva.aoc2023.utils.ReaderUtils;

import java.io.IOException;

public class PuzzleTwoSolver extends PuzzleSolver {

    public PuzzleTwoSolver(String inputFile) {
        super(inputFile);
    }

    public Long solve() {
        try {
            inputContent = ReaderUtils.readInputFile(inputFile);
            DayEightUtils.initialize(inputContent);
            DayEightUtils.updateNodesAsEndNodesWhenEndsWithZ();
            return DayEightUtils.processAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}

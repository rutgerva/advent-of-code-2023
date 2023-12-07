package com.rutgerva.aoc2023.day6;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day6.models.Race;
import com.rutgerva.aoc2023.day6.utils.DaySixUtils;
import com.rutgerva.aoc2023.utils.ReaderUtils;

import java.io.IOException;

public class PuzzleTwoSolver extends PuzzleSolver {

    public PuzzleTwoSolver(String inputFile) {
        super(inputFile);
    }

    public Long solve() {
        try {
            Long result = 1L;
            inputContent = ReaderUtils.readInputFile(inputFile);
            inputContent = DaySixUtils.cleanInputFromSpaces(inputContent);
            DaySixUtils.initializeRaces(inputContent);
            return DaySixUtils.processRaces();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}

package com.rutgerva.aoc2023.day6;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day6.utils.DaySixUtils;
import com.rutgerva.aoc2023.utils.ReaderUtils;

import java.io.IOException;

public class PuzzleOneSolver extends PuzzleSolver {

    public PuzzleOneSolver(String inputFile) {
        super(inputFile);
    }

    public Long solve() {
        try {
            inputContent = ReaderUtils.readInputFile(inputFile);
            DaySixUtils.initializeRaces(inputContent);
            return DaySixUtils.processRaces();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}

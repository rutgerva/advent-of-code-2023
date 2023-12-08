package com.rutgerva.aoc2023.day8;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day8.utils.DayEightUtils;
import com.rutgerva.aoc2023.utils.ReaderUtils;

import java.io.IOException;

public class PuzzleOneSolver extends PuzzleSolver {


    public PuzzleOneSolver(String inputFile) {
        super(inputFile);
    }


    public Integer solve() {
        try {
            inputContent = ReaderUtils.readInputFile(inputFile);
            DayEightUtils.initialize(inputContent);
            return DayEightUtils.getAmountOfStepsNeededToReachEnd();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

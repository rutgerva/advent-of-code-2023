package com.rutgerva.aoc2023.day9;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day9.utils.DayNineUtils;
import com.rutgerva.aoc2023.utils.ReaderUtils;

import java.io.IOException;

public class PuzzleOneSolver extends PuzzleSolver {


    public PuzzleOneSolver(String inputFile) {
        super(inputFile);
    }


    public Long solve() {
        try {
            inputContent = ReaderUtils.readInputFile(inputFile);
            DayNineUtils.initialize(inputContent, false);
            return DayNineUtils.processAllHistories();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}

package com.rutgerva.aoc2023.day10;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day10.utils.DayTenUtils;
import com.rutgerva.aoc2023.utils.ReaderUtils;

import java.io.IOException;

public class PuzzleOneSolver extends PuzzleSolver {


    public PuzzleOneSolver(String inputFile) {
        super(inputFile);
    }


    public Integer solve() {
        try {
            inputContent = ReaderUtils.readInputFile(inputFile);
            DayTenUtils.initialize(inputContent);
            return DayTenUtils.findMaximumDistance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

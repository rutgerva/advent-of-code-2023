package com.rutgerva.aoc2023.day11;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day11.utils.DayElevenUtils;
import com.rutgerva.aoc2023.utils.ReaderUtils;

import java.io.IOException;

public class PuzzleOneSolver extends PuzzleSolver {


    public PuzzleOneSolver(String inputFile) {
        super(inputFile);
    }


    public Integer solve() {
        try {
            inputContent = ReaderUtils.readInputFile(inputFile);
            return DayElevenUtils.process(inputContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

package com.rutgerva.aoc2023.day11;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day11.utils.DayElevenUtils;
import com.rutgerva.aoc2023.utils.ReaderUtils;

import java.io.IOException;

public class PuzzleOneSolver extends PuzzleSolver {


    public PuzzleOneSolver(String inputFile) {
        super(inputFile);
    }


    public Long solve() {
        try {
            inputContent = ReaderUtils.readInputFile(inputFile);
            DayElevenUtils.findEmptyRows(inputContent);
            DayElevenUtils.findEmptyColumns(inputContent);
            return DayElevenUtils.process(inputContent, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}

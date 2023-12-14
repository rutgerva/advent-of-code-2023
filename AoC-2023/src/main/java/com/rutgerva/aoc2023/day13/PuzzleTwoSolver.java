package com.rutgerva.aoc2023.day13;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day13.utils.DayThirteenUtils;
import com.rutgerva.aoc2023.utils.ReaderUtils;

import java.io.IOException;
import java.util.List;

public class PuzzleTwoSolver extends PuzzleSolver {

    public PuzzleTwoSolver(String inputFile) {
        super(inputFile);
    }

    public Integer solve() {
        try {
            int result = 0;
            inputContent = ReaderUtils.readInputFile(inputFile);
            List<List<String>> grids = DayThirteenUtils.initialize(inputContent);
            for (List<String> grid : grids) {
                result += DayThirteenUtils.findPerfectReflectionHorizontal(grid, true) * 100;
                result += DayThirteenUtils.findPerfectReflectionVertical(grid, true);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
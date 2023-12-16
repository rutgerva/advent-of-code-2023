package com.rutgerva.aoc2023.day16;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day15.models.Box;
import com.rutgerva.aoc2023.utils.ReaderUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PuzzleTwoSolver extends PuzzleSolver {
    List<Box> boxes = new ArrayList<>();

    public PuzzleTwoSolver(String inputFile) {
        super(inputFile);
    }

    public Integer solve() {
        try {
            inputContent = ReaderUtils.readInputFile(inputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

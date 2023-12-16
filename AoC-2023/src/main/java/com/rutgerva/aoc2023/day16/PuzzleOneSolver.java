package com.rutgerva.aoc2023.day16;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day16.enums.TravellingDirection;
import com.rutgerva.aoc2023.day16.utils.DaySixteenUtils;
import com.rutgerva.aoc2023.utils.ReaderUtils;

import java.io.IOException;

public class PuzzleOneSolver extends PuzzleSolver {


    public PuzzleOneSolver(String inputFile) {
        super(inputFile);
    }


    public Integer solve() {
        try {
            Integer result = 0;
            inputContent = ReaderUtils.readInputFile(inputFile);
            DaySixteenUtils.initialize(inputContent);
            DaySixteenUtils.traverseGrid(new int[]{0, 0}, TravellingDirection.RIGHT);
            return DaySixteenUtils.getAmountOfEnergizedTiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

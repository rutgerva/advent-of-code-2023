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
            boolean[][] traveled = DaySixteenUtils.getTraveled();
            return countTraversedGrid(traveled);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int countTraversedGrid(boolean[][] traversedGrid) {
        int count = 0;
        for (int i = 0; i < traversedGrid.length; i++) {
            for (int y = 0; y < traversedGrid[0].length; y++) {
                count += traversedGrid[i][y] ? 1 : 0;
            }
        }
        return count;
    }
}

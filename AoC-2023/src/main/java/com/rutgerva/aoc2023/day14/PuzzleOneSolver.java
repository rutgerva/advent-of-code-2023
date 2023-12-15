package com.rutgerva.aoc2023.day14;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day14.utils.DayFourteenUtils;
import com.rutgerva.aoc2023.utils.ReaderUtils;

import java.io.IOException;
import java.util.List;

public class PuzzleOneSolver extends PuzzleSolver {


    public PuzzleOneSolver(String inputFile) {
        super(inputFile);
    }


    public Integer solve() {
        try {
            Integer result = 0;
            inputContent = ReaderUtils.readInputFile(inputFile);
            List<String> gridAfterPull = DayFourteenUtils.rollNorth(inputContent);
            for (int i = 0; i < gridAfterPull.size(); i++) {
                int weight = gridAfterPull.size() - i;
                result += DayFourteenUtils.amountOfRoundRocks(gridAfterPull.get(i)) * weight;
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

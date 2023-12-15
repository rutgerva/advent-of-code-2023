package com.rutgerva.aoc2023.day14;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day14.utils.DayFourteenUtils;
import com.rutgerva.aoc2023.utils.ReaderUtils;
import com.rutgerva.aoc2023.utils.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PuzzleOneSolver extends PuzzleSolver {


    public PuzzleOneSolver(String inputFile) {
        super(inputFile);
    }


    public Integer solve() {
        try {
            Integer result = 0;
            inputContent = ReaderUtils.readInputFile(inputFile);
            List<String> gridAfterPull = DayFourteenUtils.pullLever(inputContent);
            for(int i = 0; i < gridAfterPull.size(); i++) {
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

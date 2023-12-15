package com.rutgerva.aoc2023.day15;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day15.utils.DayFifteenUtils;
import com.rutgerva.aoc2023.utils.ReaderUtils;

import java.io.IOException;
import java.util.Arrays;

public class PuzzleOneSolver extends PuzzleSolver {


    public PuzzleOneSolver(String inputFile) {
        super(inputFile);
    }


    public Integer solve() {
        try {
            Integer result = 0;
            inputContent = ReaderUtils.readInputFile(inputFile);
            for (String s : inputContent) {
                String[] values = s.split(",");
                result += Arrays.stream(values)
                        .mapToInt(DayFifteenUtils::hashString)
                        .sum();
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

package com.rutgerva.aoc2023.day12;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day12.utils.DayTwelveUtils;
import com.rutgerva.aoc2023.utils.ReaderUtils;
import com.rutgerva.aoc2023.utils.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PuzzleOneSolver extends PuzzleSolver {


    public PuzzleOneSolver(String inputFile) {
        super(inputFile);
    }


    public Long solve() {
        try {
            Long result = 0L;
            inputContent = ReaderUtils.readInputFile(inputFile);
            DayTwelveUtils.clearCache();
            for (String s : inputContent) {
                String [] conditionAndGroup = s.split(StringUtils.SPACE);
                result += DayTwelveUtils.countPermutations(conditionAndGroup[0], StringUtils.extractListOfIntegersFromString(s));
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}

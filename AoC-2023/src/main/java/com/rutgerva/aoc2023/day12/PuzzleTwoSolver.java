package com.rutgerva.aoc2023.day12;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day12.utils.DayTwelveUtils;
import com.rutgerva.aoc2023.utils.ReaderUtils;
import com.rutgerva.aoc2023.utils.StringUtils;

import java.io.IOException;

public class PuzzleTwoSolver extends PuzzleSolver {
    private final int UNFOLD_AMOUNT = 5;

    public PuzzleTwoSolver(String inputFile) {
        super(inputFile);
    }

    public Long solve() {
        try {
            Long result = 0L;
            inputContent = ReaderUtils.readInputFile(inputFile);
            DayTwelveUtils.clearCache();
            for (String s : inputContent) {
                String[] conditionAndGroup = s.split(StringUtils.SPACE);
                result += DayTwelveUtils.countPermutations(unfoldCondition(conditionAndGroup[0]),
                        StringUtils.extractListOfIntegersFromString(unfoldGroups(conditionAndGroup[1])));
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    private String unfoldCondition(String originalCondition) {
        return unfold(originalCondition, '?');
    }

    private String unfoldGroups(String groups) {
        return unfold(groups, ',');
    }

    private String unfold(String toUnfold, char separator) {
        StringBuilder unfolded = new StringBuilder(toUnfold);
        for (int i = 0; i < UNFOLD_AMOUNT - 1; i++) {
            unfolded.append(separator).append(toUnfold);
        }
        return unfolded.toString();
    }
}

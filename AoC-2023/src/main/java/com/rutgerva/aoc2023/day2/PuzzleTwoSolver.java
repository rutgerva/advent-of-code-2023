package com.rutgerva.aoc2023.day2;

import com.rutgerva.aoc2023.PuzzleSolver;
import static com.rutgerva.aoc2023.day1.utils.DayOneUtils.Digit;
import static com.rutgerva.aoc2023.day1.utils.DayOneUtils.findFirstAlphabeticalDigitInString;
import static com.rutgerva.aoc2023.day1.utils.DayOneUtils.findFirstNumericalDigitInString;
import static com.rutgerva.aoc2023.day1.utils.DayOneUtils.findLastAlphabeticalDigitInString;
import com.rutgerva.aoc2023.day2.utils.DayTwoUtils;

import java.io.BufferedReader;
import java.io.IOException;

public class PuzzleTwoSolver extends PuzzleSolver {
    public PuzzleTwoSolver(String inputFile) {
        super(inputFile);
    }

    public Integer solve() {
        BufferedReader bufferedReader = this.getBufferedReader();
        int result = 0;
        try {
            String line = bufferedReader.readLine();
            while (null != line) {
                result += DayTwoUtils.getPowerOfGame(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

package com.rutgerva.aoc2023.day1;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day1.utils.DayOneUtils;

import java.io.BufferedReader;
import java.io.IOException;

public class PuzzleOneSolver extends PuzzleSolver {

    public PuzzleOneSolver(String inputFile) {
        super(inputFile);
    }

    public Integer solve() {
        BufferedReader bufferedReader = this.getBufferedReader();
        int result = 0;
        try {
            String line = bufferedReader.readLine();
            while (null != line) {
                String firstNumber = DayOneUtils.findFirstNumericalDigitInString(line);
                //reverse to apply same logic as using "find first"
                String secondNumber = DayOneUtils.findFirstNumericalDigitInString(
                        new StringBuilder(line)
                                .reverse()
                                .toString()
                );

                result += Integer.parseInt(firstNumber + secondNumber);

                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

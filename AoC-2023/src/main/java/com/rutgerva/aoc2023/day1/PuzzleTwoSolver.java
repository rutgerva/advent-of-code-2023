package com.rutgerva.aoc2023.day1;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day1.utils.DayOneUtils;
import static com.rutgerva.aoc2023.day1.utils.DayOneUtils.Digit;
import static com.rutgerva.aoc2023.day1.utils.DayOneUtils.findFirstAlphabeticalDigitInString;
import static com.rutgerva.aoc2023.day1.utils.DayOneUtils.findFirstNumericalDigitInString;
import static com.rutgerva.aoc2023.day1.utils.DayOneUtils.findLastAlphabeticalDigitInString;

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
                // make lowercase
                line = line.toLowerCase();

                //extract first numerical and alphabetical digits
                String firstNumericalDigit = findFirstNumericalDigitInString(line);
                DayOneUtils.Digit firstAlphabeticalDigit = findFirstAlphabeticalDigitInString(line);

                StringBuilder sb = new StringBuilder(line);
                //extract last numerical and alphabetical digits (reverse can again be used to re-use find first)
                String lastNumericalDigit = findFirstNumericalDigitInString(sb.reverse().toString());
                DayOneUtils.Digit lastAlphabeticalDigit = findLastAlphabeticalDigitInString(line);
                // Get the first and last most of all findings
                String firstNumber = occursFirst(line, firstNumericalDigit, firstAlphabeticalDigit);
                String lastNumber = occursLast(line, lastNumericalDigit, lastAlphabeticalDigit);
                //concatenate and add to total
                result += Integer.parseInt(firstNumber + lastNumber);

                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Checks the very first occurrence of either the numerical or alphabetical digit on the line and returns it
     *
     * @param line              complete input line to check
     * @param numericalDigit    First numerical digit found on this line
     * @param alphabeticalDigit First alphabetical digit found on this line
     * @return The very first digit on the input line
     */
    private String occursFirst(String line, String numericalDigit, Digit alphabeticalDigit) {
        // Neither have been found
        if (!line.contains(numericalDigit) && !line.contains(alphabeticalDigit.getName())) {
            return "0";
        } else if (!line.contains(numericalDigit) && line.contains(alphabeticalDigit.getName())) {
            return alphabeticalDigit.getValue().toString();
        } else if (!line.contains(alphabeticalDigit.getName()) && line.contains(numericalDigit)) {
            return numericalDigit;
        } else {
            return line.indexOf(numericalDigit) < line.indexOf(alphabeticalDigit.getName())
                    ? numericalDigit
                    : alphabeticalDigit.getValue().toString();
        }
    }

    /**
     * Checks the very last occurrence of either the numerical or alphabetical digit on the line and returns it
     *
     * @param line              complete input line to check
     * @param numericalDigit    Last numerical digit found on this line
     * @param alphabeticalDigit Last alphabetical digit found on this line
     * @return The most last digit on the input line
     */
    private String occursLast(String line, String numericalDigit, Digit alphabeticalDigit) {
        // Neither have been found
        if (!line.contains(numericalDigit) && !line.contains(alphabeticalDigit.getName())) {
            return "0";
        } else if (!line.contains(numericalDigit) && line.contains(alphabeticalDigit.getName())) {
            return alphabeticalDigit.getValue().toString();
        } else if (!line.contains(alphabeticalDigit.getName()) && line.contains(numericalDigit)) {
            return numericalDigit;
        } else {
            return line.lastIndexOf(numericalDigit) > line.lastIndexOf(alphabeticalDigit.getName())
                    ? numericalDigit
                    : alphabeticalDigit.getValue().toString();
        }
    }
}

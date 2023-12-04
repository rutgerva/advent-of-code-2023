package com.rutgerva.aoc2023.day3;

import com.rutgerva.aoc2023.PuzzleSolver;
import static com.rutgerva.aoc2023.day3.utils.DayThreeUtils.checkNoAdjacentSymbols;
import static com.rutgerva.aoc2023.day3.utils.DayThreeUtils.initializePuzzleSolver;
import static com.rutgerva.aoc2023.day3.utils.DayThreeUtils.partNumbers;
import com.rutgerva.aoc2023.day3.utils.PartNumber;
import static java.lang.Boolean.FALSE;

import java.io.IOException;

public class PuzzleOneSolver extends PuzzleSolver {


    public PuzzleOneSolver(String inputFile) {
        super(inputFile);
    }


    public Integer solve() {
        int result = 0;
        try {
            initializePuzzleSolver(inputFile);
            result = processAllPartNumbersInEngine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Integer processAllPartNumbersInEngine() {
        int result = 0;
        for (PartNumber partNumber : partNumbers) {
            if (FALSE.equals(checkNoAdjacentSymbols(partNumber.row(), partNumber.beginIndex(), partNumber.endIndex()))) {
                result += partNumber.value();
            }
        }
        return result;
    }
}

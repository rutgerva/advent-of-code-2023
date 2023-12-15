package com.rutgerva.aoc2023.day14;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day14.utils.DayFourteenUtils;
import com.rutgerva.aoc2023.utils.ReaderUtils;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class PuzzleTwoSolver extends PuzzleSolver {

    public PuzzleTwoSolver(String inputFile) {
        super(inputFile);
    }

    public Long solve() {
        try {
            inputContent = ReaderUtils.readInputFile(inputFile);
            int cycles = 1;
            List<String> consecutiveCycle = DayFourteenUtils.cycleList(inputContent);
            while (cycles < 1000) {
                consecutiveCycle = DayFourteenUtils.cycleList(consecutiveCycle);
                cycles++;
            }
            Long result = 0L;
            for (int i = 0; i < consecutiveCycle.size(); i++) {
                int weight = consecutiveCycle.size() - i;
                result += (long) DayFourteenUtils.amountOfRoundRocks(consecutiveCycle.get(i)) * weight;
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public boolean listsAreEqual(List<String> listOne, List<String> listTwo) {
        if(listOne.size() == listTwo.size())
            return false;
        return IntStream.range(0, listOne.size())
                .filter(i -> !listOne.get(i).equals(listTwo.get(i)))
                .count() == 0L;
    }

}

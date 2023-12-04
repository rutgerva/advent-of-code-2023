package com.rutgerva.aoc2023.day4;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day4.utils.DayFourUtils;
import static com.rutgerva.aoc2023.day4.utils.DayFourUtils.*;
import com.rutgerva.aoc2023.day4.utils.ScratchCard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PuzzleTwoSolver extends PuzzleSolver {

    public PuzzleTwoSolver(String inputFile) {
        super(inputFile);
    }

    public Integer solve() {
        List<ScratchCard> scratchCardObjList = new ArrayList<>();
        try {
            readInput(inputFile);
            for(String scratchCard: scratchCards) {
                String[] numbers = removeCardPrefix(scratchCard).split("\\|");
                List<Integer> winningNumbers = extractNumbersFromCard(numbers[0]);
                List<Integer> scratchCardNumbers = extractNumbersFromCard(numbers[1]);
                int matches = winningMatches(winningNumbers, scratchCardNumbers);
                scratchCardObjList.add(new ScratchCard(winningNumbers, scratchCardNumbers, matches, 1));
            }
            int index = 0;
            for(ScratchCard scratchCard: scratchCardObjList) {
                for (int i = index + 1; i < index + scratchCard.getMatches() + 1; ++i) {
                    scratchCardObjList.get(i).addCopies(scratchCard.getCopies());
                }
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scratchCardObjList.stream()
                .mapToInt(ScratchCard::getCopies)
                .sum();
    }
}

package com.rutgerva.aoc2023.day4;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day4.utils.DayFourUtils;
import static com.rutgerva.aoc2023.day4.utils.DayFourUtils.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PuzzleOneSolver extends PuzzleSolver {

    private List<Integer> cardNumbers = new ArrayList<>();

    public PuzzleOneSolver(String inputFile) {
        super(inputFile);
    }


    public Integer solve() {
        int result = 0;
        try {
            readInput(inputFile);
            for(String scratchCard : scratchCards) {
                result += processScratchCard(scratchCard);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Integer processScratchCard(String scratchCard) throws Exception {
        String[] numbers = removeCardPrefix(scratchCard).split("\\|");
        List<Integer> winningNumbers = extractNumbersFromCard(numbers[0]);
        List<Integer> scratchCardNumbers = extractNumbersFromCard(numbers[1]);
        int matches = winningMatches(winningNumbers, scratchCardNumbers);
        if (matches != 0) {
            return (int) Math.pow(2, matches - 1.0);
        }
        return 0;
    }
}

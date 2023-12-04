package com.rutgerva.aoc2023.day4.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayFourUtils {
    public static List<String> scratchCards;

    public static void readInput(String inputFile) throws IOException {
        //Read file
        scratchCards = Files.readAllLines(Paths.get("src/main/resources/" + inputFile));
        if (!scratchCards.isEmpty()) {
        }
    }

    public static List<Integer> extractNumbersFromCard(String numbers) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(numbers);

        return matcher.results()
                .map(MatchResult::group)
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
    }

    public static String removeCardPrefix(String scratchCard) {
        //remove Card x: from line
        Pattern pattern = Pattern.compile("Card.*\\d+:");
        Matcher matcher = pattern.matcher(scratchCard);
        if(matcher.find()) {
            return scratchCard.replace(matcher.group(), "");
        } else {
            throw new RuntimeException("Card prefix could not be removed or found.");
        }
    }

    public static Integer winningMatches(List<Integer> winningNumbers, List<Integer> playedNumbers) {
        int matches = 0;
        for(Integer winningNumber: winningNumbers) {
            if(playedNumbers.contains(winningNumber)) {
                matches++;
            }
        }
        return matches;
    }
}

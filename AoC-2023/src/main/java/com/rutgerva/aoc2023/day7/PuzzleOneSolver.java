package com.rutgerva.aoc2023.day7;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day7.models.Hand;
import com.rutgerva.aoc2023.day7.models.HandType;
import static com.rutgerva.aoc2023.day7.utils.DaySevenUtils.hands;
import static com.rutgerva.aoc2023.day7.utils.DaySevenUtils.readInput;

import java.io.IOException;
import java.util.List;

public class PuzzleOneSolver extends PuzzleSolver {
    private Integer rank;

    public PuzzleOneSolver(String inputFile) {
        super(inputFile);
    }

    public Integer solve() {
        try {
            Integer result = 0;
            readInput(inputFile);
            rank = hands.size();

            result += processHandType(HandType.FIVE_OF_A_KIND);
            result += processHandType(HandType.FOUR_OF_A_KIND);
            result += processHandType(HandType.FULL_HOUSE);
            result += processHandType(HandType.THREE_OF_A_KIND);
            result += processHandType(HandType.TWO_PAIR);
            result += processHandType(HandType.ONE_PAIR);
            result += processHandType(HandType.HIGH_CARD);

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Integer processHandType(HandType type) {
        Integer result = 0;
        List<Hand> handsByTypeAndRank = hands.stream()
                .filter(hand -> hand.getHandType().equals(type))
                .sorted((o1, o2) -> Hand.isStrongerThanHandOfSameType(o1, o2) ? -1 : 1)
                .toList();

        for (Hand hand : handsByTypeAndRank) {
            result += rank * hand.getBid();
            rank--;
        }
        return result;
    }
}

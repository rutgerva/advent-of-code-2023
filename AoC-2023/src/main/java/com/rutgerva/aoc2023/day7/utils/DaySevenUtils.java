package com.rutgerva.aoc2023.day7.utils;

import com.rutgerva.aoc2023.day7.models.Hand;
import com.rutgerva.aoc2023.day7.models.HandType;
import com.rutgerva.aoc2023.utils.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class DaySevenUtils {

    private static List<Hand> hands;
    private static String cardStrengthsDefinition;
    private static Integer rank;


    private DaySevenUtils() {
    }

    public static void initialize(List<String> input, final String strengthsDefinition) {
        hands = new ArrayList<>();
        for (String line : input) {
            String[] handWithBid = line.split(StringUtils.SPACE);
            hands.add(new Hand(handWithBid[0], Integer.parseInt(handWithBid[1])));
        }
        //sort by hand type
        hands.sort(Comparator.comparing(hand -> hand.getHandType().ordinal()));
        rank = hands.size();
        cardStrengthsDefinition = strengthsDefinition;
    }

    public static void promoteHands() {
        for (Hand hand : hands) {
            hand.promote();
        }
        //re-sort with new promotions
        hands.sort(Comparator.comparing(hand -> hand.getHandType().ordinal()));
    }

    public static Integer processHands() {

        Integer result = processHandType(HandType.FIVE_OF_A_KIND);
        result += processHandType(HandType.FOUR_OF_A_KIND);
        result += processHandType(HandType.FULL_HOUSE);
        result += processHandType(HandType.THREE_OF_A_KIND);
        result += processHandType(HandType.TWO_PAIR);
        result += processHandType(HandType.ONE_PAIR);
        result += processHandType(HandType.HIGH_CARD);

        return result;
    }

    private static Integer processHandType(HandType type) {
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

    public static String getCardStrengthsDefinition() {
        return cardStrengthsDefinition;
    }
}

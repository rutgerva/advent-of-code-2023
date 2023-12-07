package com.rutgerva.aoc2023.day7.models;

import static com.rutgerva.aoc2023.day7.models.HandType.FIVE_OF_A_KIND;
import static com.rutgerva.aoc2023.day7.models.HandType.FOUR_OF_A_KIND;
import static com.rutgerva.aoc2023.day7.models.HandType.FULL_HOUSE;
import static com.rutgerva.aoc2023.day7.models.HandType.ONE_PAIR;
import static com.rutgerva.aoc2023.day7.models.HandType.THREE_OF_A_KIND;
import com.rutgerva.aoc2023.day7.utils.DaySevenUtils;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hand {
    //entire hand represented as list to perform ordered operations on
    private final List<Character> cards;
    // type of hand to determine rank later on
    @Getter
    private HandType handType;
    // bid of the hand
    @Getter
    private final Integer bid;
    private final Integer jokers;

    public Hand(String cards, Integer bid) {
        this.cards = cards.chars()
                .mapToObj(c -> (char) c)
                .toList();

        jokers = (int) this.cards.stream()
                .filter(c -> c.equals('J'))
                .count();

        this.bid = bid;
        //determine hand type after initialization of object
        determineHandType();
    }

    /**
     * Determines the hand type of the current instance under the assumption a hand is 5 cards.
     * Checks for unique amount of cards within the hand
     * When 4 unique cards are found it implies there is 1 pair
     * When 3 unique cards are found and either of these values occur 3 times in the hand, we have three of a kind, else we have two pairs
     * When 2 unique cards are found and either of these values occurs 3 times in the hand, we have a full house else it must be four of a kind
     * When 1 unique card is found it implies we have five of a kind
     * In other cases we only have a high card
     */
    private void determineHandType() {
        Set<Character> uniqueValues = new HashSet<>(cards);

        handType = switch (uniqueValues.size()) {
            case 4 -> HandType.ONE_PAIR;
            case 3 -> {
                List<Character> filteredList = new ArrayList<>(uniqueValues);
                if (checkOccurrences(filteredList.get(0)) == 3
                        || checkOccurrences(filteredList.get(1)) == 3
                        || checkOccurrences(filteredList.get(2)) == 3)
                    yield HandType.THREE_OF_A_KIND;
                else
                    yield HandType.TWO_PAIR;
            }
            case 2 -> {
                List<Character> filteredList = new ArrayList<>(uniqueValues);
                if (checkOccurrences(filteredList.get(0)) == 3 || checkOccurrences(filteredList.get(1)) == 3)
                    yield HandType.FULL_HOUSE;
                else
                    yield HandType.FOUR_OF_A_KIND;
            }
            case 1 -> HandType.FIVE_OF_A_KIND;
            default -> HandType.HIGH_CARD;
        };
    }

    public static boolean isStrongerThanHandOfSameType(Hand toCheck, Hand competitor) {

        if (!competitor.handType.equals(toCheck.handType)) {
            throw new RuntimeException("Trying to compare strength of hand on different hand types.");
        }

        for (int i = 0; i < toCheck.cards.size(); i++) {
            Integer myStrength = strength(toCheck.cards.get(i));
            Integer competitorStrength = strength(competitor.cards.get(i));
            if (myStrength > competitorStrength)
                return true;
            else if (myStrength < competitorStrength)
                return false;
        }
        return true;
    }

    private static Integer strength(Character card) {
        return DaySevenUtils.getCardStrengthsDefinition().indexOf(card);
    }

    private int checkOccurrences(Character toCheck) {
        return (int) cards.stream()
                .filter(c -> c.equals(toCheck))
                .count();
    }

    /**
     * Promotes the hand type of the instance based on the amount of jokers it contains.
     * HIGH_CARD: There can be at most 1 joker, hence promotion to ONE_PAIR
     * ONE_PAIR: There can be at most 2 jokers, hence only possible to promote to THREE_OF_A_KIND
     * TWO_PAIR: There can be at most 2 jokers, given on of the pairs are jokers, promote to FOUR_OF_A_KIND else FULL_HOUSE
     * THREE_OF_A_KIND: There can be at most 3 jokers, In case 3 or 1 jokers are present FOUR_OF_A_KIND can be formed, with 2 jokers promote to FIVE_OF_A_KIND
     * Other: Any possible combination of present jokers will lead to FIVE_OF_A_KIND
     */
    public void promote() {
        if (jokers > 0) {
            this.handType = switch (handType) {
                case HIGH_CARD -> ONE_PAIR;
                case ONE_PAIR -> THREE_OF_A_KIND;
                case TWO_PAIR -> jokers == 2 ? FOUR_OF_A_KIND : FULL_HOUSE;
                case THREE_OF_A_KIND -> jokers == 3 || jokers == 1 ? FOUR_OF_A_KIND : FIVE_OF_A_KIND;
                case FULL_HOUSE, FOUR_OF_A_KIND, FIVE_OF_A_KIND -> FIVE_OF_A_KIND;
            };
        }
    }
}

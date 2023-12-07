package com.rutgerva.aoc2023.day7.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hand {
    //labels by their position individually
    private final Character firstLabel;
    private final Character secondLabel;
    private final Character thirdLabel;
    private final Character fourthLabel;
    private final Character fifthLabel;
    //entire hand represented as list to perform ordered operations on
    private List<Character> cards;
    // type of hand to determine rank later on
    @Getter
    private HandType handType;
    // bid of the hand
    @Getter
    private Integer bid;

    public Hand(String cards, Integer bid) {
        this.cards = cards.chars()
                .mapToObj(c -> (char) c)
                .toList();

        firstLabel = this.cards.get(0);
        secondLabel = this.cards.get(1);
        thirdLabel = this.cards.get(2);
        fourthLabel = this.cards.get(3);
        fifthLabel = this.cards.get(4);

        this.bid = bid;
        //determine hand type after initialization of object
        determineHandType();
    }

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

        if(!competitor.handType.equals(toCheck.handType)) {
            throw new RuntimeException("Trying to compare strenght of hand on different hand types.");
        }

        for(int i = 0; i < toCheck.cards.size(); i++) {
            Integer myStrength = strength(toCheck.cards.get(i));
            Integer competitorStrength = strength(competitor.cards.get(i));
            if( myStrength > competitorStrength)
                return true;
            else if(myStrength < competitorStrength)
                return false;
        }
        return true;
    }

    private static Integer strength(Character card) {
        String ascendingStrengthOfCards = "23456789TJQKA";
        return ascendingStrengthOfCards.indexOf(card);
    }

    private int checkOccurrences(Character toCheck) {
        return (int) cards.stream()
                .filter(c -> c.equals(toCheck))
                .count();
    }
}

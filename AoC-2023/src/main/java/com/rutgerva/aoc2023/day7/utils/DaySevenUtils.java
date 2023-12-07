package com.rutgerva.aoc2023.day7.utils;

import com.rutgerva.aoc2023.day7.models.Hand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DaySevenUtils {
    public static List<String> input;

    public static List<Hand> hands;

    public static String cardStrengths;

    public static void readInput(String inputFile) throws IOException {
        //Read file
        input = new ArrayList<>();
        input = Files.readAllLines(Paths.get("src/main/resources/" + inputFile));
        initialize();
    }

    public static void initialize() {
        hands = new ArrayList<>();
        for (String line : input) {
            String[] handWithBid = line.split(" ");
            hands.add(new Hand(handWithBid[0], Integer.parseInt(handWithBid[1])));
        }
        //sort by hand type
        hands.sort(Comparator.comparing(hand -> hand.getHandType().ordinal()));
    }

    public static void promoteHands() {
        for (Hand hand : hands) {
            hand.promote();
        }
        //re-sort with new promotions
        hands.sort(Comparator.comparing(hand -> hand.getHandType().ordinal()));
    }

    //249936761 too high
}

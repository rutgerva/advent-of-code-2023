package com.rutgerva.aoc2023.day12.utils;

import static com.rutgerva.aoc2023.day12.enums.ConditionSymbol.BROKEN;
import static com.rutgerva.aoc2023.day12.enums.ConditionSymbol.OPERATIONAL;
import static com.rutgerva.aoc2023.day12.enums.ConditionSymbol.UNKNOWN;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DayTwelveUtils {
    private static Map<ImmutablePair<String, List<Integer>>, Long> cache;

    private DayTwelveUtils() {
    }

    public static void clearCache() {
        cache = new HashMap<>();
    }

    public static long countPermutations(String condition, List<Integer> groups) {
        ImmutablePair conditionWithGroup = new ImmutablePair(condition, groups);
        //If this condition is already found once, return it
        if (cache.containsKey(conditionWithGroup))
            return cache.get(conditionWithGroup);
        //End condition of the recursion
        if (condition.isBlank())
            return groups.isEmpty() ? 1 : 0; // check if all groups are processed, if so valid combo

        long permutations = 0; //assume invalid
        char firstChar = condition.charAt(0);
        if (OPERATIONAL.equals(firstChar))
            permutations = countPermutations(condition.substring(1), groups);
        else if (UNKNOWN.equals(firstChar)) {
            //check both scenario's for unknown
            permutations = countPermutations(OPERATIONAL.getSymbol() + condition.substring(1), groups)
                    + countPermutations(BROKEN.getSymbol() + condition.substring(1), groups);
        } else if (BROKEN.equals(firstChar) && (!groups.isEmpty())) {
            int amountOfDamagedSprings = groups.get(0);
            //If we have at least the amount needed in the group and it can be a valid series of broken springs
            if (condition.length() >= amountOfDamagedSprings &&
                    condition.chars().limit(amountOfDamagedSprings)
                            .allMatch(c -> BROKEN.equals((char) c) || UNKNOWN.equals((char) c))) {
                groups = groups.subList(1, groups.size());
                if (amountOfDamagedSprings == condition.length())
                    permutations = groups.isEmpty() ? 1 : 0;
                else if (OPERATIONAL.equals(condition.charAt(amountOfDamagedSprings))) {
                    permutations = countPermutations(condition.substring(amountOfDamagedSprings + 1), groups);
                } else if (UNKNOWN.equals(condition.charAt(amountOfDamagedSprings))) {
                    //must be operational to remain valid
                    permutations = countPermutations(OPERATIONAL.getSymbol() + condition.substring(amountOfDamagedSprings + 1), groups);
                }
            }

        }
        cache.put(conditionWithGroup, permutations);
        return permutations;
    }
}

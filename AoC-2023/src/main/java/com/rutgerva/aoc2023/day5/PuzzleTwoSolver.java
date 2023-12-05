package com.rutgerva.aoc2023.day5;

import com.rutgerva.aoc2023.PuzzleSolver;
import static com.rutgerva.aoc2023.day4.utils.DayFourUtils.readInput;
import static com.rutgerva.aoc2023.day5.utils.DayFiveUtils.getSeedList;
import static com.rutgerva.aoc2023.day5.utils.DayFiveUtils.initializeMappings;
import static com.rutgerva.aoc2023.day5.utils.DayFiveUtils.mapSeedToLocation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class PuzzleTwoSolver extends PuzzleSolver {
    private static Long result = Long.MAX_VALUE;
    public PuzzleTwoSolver(String inputFile) {
        super(inputFile);
    }

    public Long solve() {
        try {
            readInput(inputFile);
            initializeMappings();
            List<Long> seedAndRangePairs = getSeedList();
            if (seedAndRangePairs.size() % 2 != 0)
                throw new RuntimeException("Something is wrong with seed list input," +
                        " cannot be broken up into pairs");
            List<CompletableFuture<Long>> futures = new ArrayList<>();
            for (int i = 0; i < seedAndRangePairs.size(); i += 2) {
                long start = seedAndRangePairs.get(i);
                long end = start + seedAndRangePairs.get(i + 1);
                CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> computeLowestLocationOfRange(start, end));
                futures.add(future);
            }

            // Combine the results of all CompletableFuture objects
            CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
            allOf.thenRun(() -> {
                for(CompletableFuture<Long> future : futures) {
                    try {
                        Long lowestLocationFromRange = future.get();
                        result = result > lowestLocationFromRange ? lowestLocationFromRange : result;
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            allOf.get();
            return result;
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    private static Long computeLowestLocationOfRange(Long start, Long end) {
        Long currentLowest = Long.MAX_VALUE;
        for (long seed = start; seed < end; seed++) {
            Long locationCandidate = mapSeedToLocation(seed);
            currentLowest = currentLowest > locationCandidate ? locationCandidate : currentLowest;
        }
        return currentLowest;
    }
}

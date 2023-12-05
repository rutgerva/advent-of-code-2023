package com.rutgerva.aoc2023.day5;

import com.rutgerva.aoc2023.PuzzleSolver;
import static com.rutgerva.aoc2023.day5.utils.DayFiveUtils.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PuzzleOneSolver extends PuzzleSolver {

    private List<Long> locations = new ArrayList<>();

    public PuzzleOneSolver(String inputFile) {
        super(inputFile);
    }


    public Long solve() {
        try {
            readInput(inputFile);
            List<Long> seedList = getSeedList();
            initializeMappings();
            for (Long seed : seedList) {
                locations.add(mapSeedToLocation(seed));
            }
            return Collections.min(locations);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}

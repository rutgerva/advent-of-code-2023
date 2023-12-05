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
                Long mappedValue = mapSourceToDestination(seed, seedToSoil);
                //chain of mappings
                mappedValue = mapSourceToDestination(mappedValue, soilToFert);
                mappedValue = mapSourceToDestination(mappedValue, fertToWater);
                mappedValue = mapSourceToDestination(mappedValue, waterToLight);
                mappedValue = mapSourceToDestination(mappedValue, lightToTemp);
                mappedValue = mapSourceToDestination(mappedValue, tempToHumidity);
                mappedValue = mapSourceToDestination(mappedValue, humidityToLocation);
                //add the actual location into a list of found locations
                locations.add(mappedValue);
            }
            return Collections.min(locations);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }
}

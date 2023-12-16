package com.rutgerva.aoc2023.day16;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day16.enums.TravellingDirection;
import com.rutgerva.aoc2023.day16.utils.DaySixteenUtils;
import com.rutgerva.aoc2023.utils.ReaderUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PuzzleTwoSolver extends PuzzleSolver {

    private List<Integer> configurations = new ArrayList<>();

    public PuzzleTwoSolver(String inputFile) {
        super(inputFile);
    }

    public Integer solve() {
        try {
            inputContent = ReaderUtils.readInputFile(inputFile);
            List<ImmutablePair<int[], TravellingDirection>> possibleEntryPoints
                    = calculateOuterBoundsIndexes(DaySixteenUtils.getGrid());
            int result = 0;
            for (ImmutablePair<int[], TravellingDirection> pair : possibleEntryPoints) {
                DaySixteenUtils.initialize(inputContent);
                DaySixteenUtils.traverseGrid(pair.left, pair.right);
                int configurationEnergy = DaySixteenUtils.getAmountOfEnergizedTiles();
                result = Math.max(result, configurationEnergy);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<ImmutablePair<int[], TravellingDirection>> calculateOuterBoundsIndexes(char[][] array) {
        List<ImmutablePair<int[], TravellingDirection>> result = new ArrayList<>();

        int rows = array.length;
        int cols = array[0].length;

        // Iterate through the first and last rows
        for (int i = 0; i < rows; i++) {
            result.add(new ImmutablePair<>(new int[]{i, 0}, TravellingDirection.RIGHT));        // Left edge
            result.add(new ImmutablePair<>(new int[]{i, cols - 1}, TravellingDirection.LEFT)); // Right edge
        }

        // Iterate through the first and last columns
        for (int j = 0; j < cols; j++) {
            result.add(new ImmutablePair<>(new int[]{0, j}, TravellingDirection.DOWN));        // Top edge
            result.add(new ImmutablePair<>(new int[]{rows - 1, j}, TravellingDirection.UP)); // Bottom edge
        }

        return result;
    }
}

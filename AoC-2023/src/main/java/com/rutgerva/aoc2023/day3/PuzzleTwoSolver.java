package com.rutgerva.aoc2023.day3;

import com.rutgerva.aoc2023.PuzzleSolver;
import static com.rutgerva.aoc2023.day3.utils.DayThreeUtils.gearRatios;
import static com.rutgerva.aoc2023.day3.utils.DayThreeUtils.initializePuzzleSolver;
import static com.rutgerva.aoc2023.day3.utils.DayThreeUtils.isAdjacentField;
import static com.rutgerva.aoc2023.day3.utils.DayThreeUtils.partNumbers;
import com.rutgerva.aoc2023.day3.utils.GearRatio;
import com.rutgerva.aoc2023.day3.utils.PartNumber;

import java.io.IOException;

public class PuzzleTwoSolver extends PuzzleSolver {

    public PuzzleTwoSolver(String inputFile) {
        super(inputFile);
    }

    public Integer solve() {
        int result = 0;
        try {
            initializePuzzleSolver(inputFile);
            return processAllGearRatiosInEngine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Integer processAllGearRatiosInEngine() {
        for (GearRatio gearRatio : gearRatios) {
            int firstPartNumber = 0;
            int secondPartNumber = 0;

            for (PartNumber partNumber : partNumbers) {
                if (isAdjacentField(gearRatio.getXIndex(), gearRatio.getYIndex(),
                        partNumber.row(), partNumber.beginIndex(), partNumber.endIndex())) {
                    if (firstPartNumber == 0) {
                        firstPartNumber = partNumber.value();
                    } else if (secondPartNumber == 0) {
                        secondPartNumber = partNumber.value();
                    } else {
                        firstPartNumber = 0;
                        secondPartNumber = 0;
                        break;
                    }
                }
            }
            gearRatio.setRatio(firstPartNumber * secondPartNumber);
        }
        return gearRatios.stream()
                .mapToInt(GearRatio::getRatio)
                .sum();
    }
}

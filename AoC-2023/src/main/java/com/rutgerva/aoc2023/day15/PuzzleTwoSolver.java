package com.rutgerva.aoc2023.day15;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day15.utils.DayFifteenUtils;
import com.rutgerva.aoc2023.day15.models.Box;
import com.rutgerva.aoc2023.utils.ReaderUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuzzleTwoSolver extends PuzzleSolver {
    List<Box> boxes = new ArrayList<>();

    public PuzzleTwoSolver(String inputFile) {
        super(inputFile);
    }

    public Integer solve() {
        try {
            inputContent = ReaderUtils.readInputFile(inputFile);
            initializeBoxes();
            for (String s : inputContent) {
                String[] values = s.split(",");
                Arrays.stream(values)
                        .forEach(this::processSequence);
            }
            return boxes.stream()
                    .mapToInt(Box::calculateFocussingPower)
                    .sum();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void processSequence(String sequence) {
        if (sequence.contains("="))
            processEqualSign(sequence);
        else
            processMinusSign(sequence);
    }

    private void processEqualSign(String sequence) {
        String[] labelAndFocal = sequence.split("=");
        int hash = DayFifteenUtils.hashString(labelAndFocal[0]);
        Box destinationBox = getBox(hash);
        destinationBox.addLens(labelAndFocal[0], Integer.valueOf(labelAndFocal[1]));
    }

    private void processMinusSign(String sequence) {
        String label = sequence.substring(0, sequence.length() - 1);
        int hash = DayFifteenUtils.hashString(label);
        Box destinationBox = getBox(hash);
        destinationBox.removeLens(label);
    }

    private void initializeBoxes() {
        for (int i = 0; i < 256; i++) {
            boxes.add(new Box(i));
        }
    }

    private Box getBox(int number) {
        return boxes.stream()
                .filter(box -> box.getBoxNumber() == number)
                .toList().get(0);
    }

}

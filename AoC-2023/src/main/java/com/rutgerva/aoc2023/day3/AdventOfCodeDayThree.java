package com.rutgerva.aoc2023.day3;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdventOfCodeDayThree {

    private static String INPUT_FILE = "aoc_day3_input.txt";

    public static void main(String... args) {
        PuzzleOneSolver solver1 = new PuzzleOneSolver(INPUT_FILE);
        PuzzleTwoSolver solver2 = new PuzzleTwoSolver(INPUT_FILE);

        System.out.println("The output of puzzle 1 is: " + solver1.solve());
        System.out.println("The output of puzzle 2 is: " + solver2.solve());
    }

}

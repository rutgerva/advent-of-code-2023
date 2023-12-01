package com.rutgerva.aoc2023;

import com.rutgerva.aoc2023.utils.BufferedReaderHelper;

import java.io.BufferedReader;

public abstract class PuzzleSolver {
    private final BufferedReader bufferedReader;
    protected PuzzleSolver(String inputFile) {
        this.bufferedReader = BufferedReaderHelper.build(inputFile);
    }

    protected BufferedReader getBufferedReader() {
        return this.bufferedReader;
    }
    public abstract Integer solve();
}

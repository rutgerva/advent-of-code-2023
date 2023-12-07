package com.rutgerva.aoc2023;

import com.rutgerva.aoc2023.utils.BufferedReaderHelper;

import java.io.BufferedReader;
import java.util.List;

public abstract class PuzzleSolver {
    private final BufferedReader bufferedReader;
    protected final String inputFile;
    protected List<String> inputContent;

    protected PuzzleSolver(String inputFile) {
        this.bufferedReader = BufferedReaderHelper.build(inputFile);
        this.inputFile = inputFile;
    }

    protected BufferedReader getBufferedReader() {
        return this.bufferedReader;
    }

    public abstract <T extends Number> T solve();
}

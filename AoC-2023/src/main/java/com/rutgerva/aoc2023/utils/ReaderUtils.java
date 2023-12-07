package com.rutgerva.aoc2023.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public final class ReaderUtils {
    private static final String PREFIX = "src/main/resources/";

    private ReaderUtils() {
    }

    public static List<String> readInputFile(String inputFile) throws IOException {
        return Files.readAllLines(Paths.get(PREFIX + inputFile));
    }
}

package com.rutgerva.aoc2023.day9.models;

import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class History {

    @Setter
    List<Long> historyValues = new ArrayList<>();

    public void addToHistory(Long value) {
        historyValues.add(value);
    }

    public void reverseHistory() {
        historyValues = IntStream.range(0, historyValues.size())
                .map(i -> historyValues.size() - 1-i)
                .mapToObj(historyValues::get)
                .toList();
    }

    public History extrapolate() {
        History extrapolatedHistory = new History();
        for (int i = 0; i < this.historyValues.size() - 1; ++i) {
            long stepSize = this.historyValues.get(i + 1) - this.historyValues.get(i);
            extrapolatedHistory.addToHistory(stepSize);
        }

        return extrapolatedHistory;
    }

    public boolean allZerosHistory() {
        return historyValues.stream()
                .noneMatch((num -> !num.equals(0L)));
    }

    public Long getLastValue() {
        return historyValues.get(historyValues.size() - 1);
    }
}
package com.rutgerva.aoc2023.day9.models;

import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class History {

    @Setter
    List<Long> historyValues = new ArrayList<>();

    public void addToHistory(Long value) {
        historyValues.add(value);
    }

    public void reverseHistory() {
        historyValues = historyValues.stream()
                        .sorted(Collections.reverseOrder())
                .toList();
    }

    //359725676 too low
//1898776583
//2791781968 too high
    public History extrapolate() {
        History extrapolatedHistory = new History();
        for (int i = 0; i < this.historyValues.size() - 1; ++i) {
            long stepSize = this.historyValues.get(i + 1) - this.historyValues.get(i);
            extrapolatedHistory.addToHistory(stepSize);
        }

        return extrapolatedHistory;
    }

    public History extrapolateReverse() {
        History extrapolatedHistory = new History();
        for (int i = 0; i < this.historyValues.size() - 1; ++i) {
            long stepSize = this.historyValues.get(i) - this.historyValues.get(i + 1);
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

    public Long getFirstValue() {
        return historyValues.get(0);
    }
}
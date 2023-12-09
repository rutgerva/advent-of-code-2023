package com.rutgerva.aoc2023.day9.utils;

import com.rutgerva.aoc2023.day9.models.History;
import com.rutgerva.aoc2023.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public final class DayNineUtils {

    private static List<History> historiesToProcess = new ArrayList<>();

    private DayNineUtils() {
    }

    public static void initialize(List<String> input) {
        for (String line : input) {
            History history = new History();

            List<Long> historyValues = StringUtils.extractListOfLongWithSignFromString(line);
            history.setHistoryValues(historyValues);
            historiesToProcess.add(history);
        }
    }

    public static Long processAllHistories() {
        List<Long> extrapolatedValues = new ArrayList<>();
        for (History history : historiesToProcess) {
            Long newValue = history.getLastValue();
            History extrapolatedHistory = history.extrapolate();
            while (!extrapolatedHistory.allZerosHistory()) {
                newValue += extrapolatedHistory.getLastValue();
                extrapolatedHistory = extrapolatedHistory.extrapolate();
            }
            extrapolatedValues.add(newValue);
        }
        return extrapolatedValues.stream()
                .mapToLong(Long::longValue)
                .sum();
    }
}

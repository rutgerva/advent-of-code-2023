package com.rutgerva.aoc2023.day11.enums;

import static com.rutgerva.aoc2023.day11.utils.DayElevenUtils.EMPTY_SPACE_SYMBOL;
import static com.rutgerva.aoc2023.day11.utils.DayElevenUtils.GALAXY_SYMBOL;

public enum DataPointType {
    EMPTY_SPACE(EMPTY_SPACE_SYMBOL),
    GALAXY(GALAXY_SYMBOL);

    DataPointType(Character symbol) {
        this.symbol = symbol;
    }

    private Character symbol;
}

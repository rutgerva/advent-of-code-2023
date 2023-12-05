package com.rutgerva.aoc2023.day5.utils;

import lombok.Data;

@Data
public class Mapping {
    private Long destinationRangeStart;
    private Long sourceRangeStart;
    private Long rangeLength;

    public Mapping(Long destinationRangeStart, Long sourceRangeStart, Long rangeLength) {
        this.destinationRangeStart = destinationRangeStart;
        this.sourceRangeStart = sourceRangeStart;
        this.rangeLength = rangeLength;
    }

    public boolean isSourceInMapping(Long toCheck) {
        return toCheck >= sourceRangeStart && toCheck <= sourceRangeStart + rangeLength - 1;
    }

    public Long mapping(Long toMap) {
        return isSourceInMapping(toMap)
                ? destinationRangeStart + (toMap - sourceRangeStart)
                : toMap;
    }
}

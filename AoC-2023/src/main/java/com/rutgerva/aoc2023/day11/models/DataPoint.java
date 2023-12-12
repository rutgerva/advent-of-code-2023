package com.rutgerva.aoc2023.day11.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DataPoint {

    private final Integer galaxyNumber;
    private final Integer xCoordinate;
    private final Integer yCoordinate;
}

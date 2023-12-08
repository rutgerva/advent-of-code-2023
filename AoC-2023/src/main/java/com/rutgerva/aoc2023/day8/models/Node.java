package com.rutgerva.aoc2023.day8.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;

@RequiredArgsConstructor
public class Node {
    @Getter
    private final String currentNodeLabel;
    private final ImmutablePair<String, String> navigation;

    public String navigateLeft() {
        return navigation.left;
    }

    public String navigateRight() {
        return navigation.right;
    }
}

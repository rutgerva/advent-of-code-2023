package com.rutgerva.aoc2023.day8.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class Node {
    @Getter
    private final String currentNodeLabel;
    private final ImmutablePair<String, String> navigation;
    @Getter
    @Setter
    private boolean endNode;

    public Node(String currentNodeLabel, ImmutablePair<String, String> navigation) {
        if (currentNodeLabel.equals("ZZZ"))
            endNode = true;
        this.currentNodeLabel = currentNodeLabel;
        this.navigation = navigation;
    }

    public String navigateLeft() {
        return navigation.left;
    }

    public String navigateRight() {
        return navigation.right;
    }
}

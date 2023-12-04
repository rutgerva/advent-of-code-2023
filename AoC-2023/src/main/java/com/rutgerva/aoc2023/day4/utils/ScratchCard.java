package com.rutgerva.aoc2023.day4.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ScratchCard {
    private List<Integer> winningNumbers;
    private List<Integer> scratchCardNumbers;
    private Integer matches;
    private Integer copies;

    public void addCopies(int amount) {
        this.copies += amount;
    }
}

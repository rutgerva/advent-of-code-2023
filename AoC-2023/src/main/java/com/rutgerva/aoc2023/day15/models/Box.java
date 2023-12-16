package com.rutgerva.aoc2023.day15.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class Box {
    private final int boxNumber;
    List<Lens> lenses = new ArrayList<>();

    public void addLens(String label, Integer focalLength) {
        int index = this.indexOf(label);
        if (index != -1) {
            lenses.remove(index);
            lenses.add(index, new Lens(label, focalLength));
        } else {
            lenses.add(new Lens(label, focalLength));
        }
    }

    public void removeLens(String label) {
        lenses.removeIf(lens -> lens.label().equals(label));
    }

    public int calculateFocussingPower() {
        int result = 0;
        for (int i = 0; i < lenses.size(); i++) {
            //box number * slot number * focal length
            result += (boxNumber + 1) * (i + 1) * lenses.get(i).focalLength();
        }
        return result;
    }

    /**
     * Retrieves index of the lens with a given label
     *
     * @return index of the lens with given label, -1 if not lens is not inside the box.
     */
    private int indexOf(String label) {
        return lenses.indexOf(lenses.stream()
                .filter(lens -> lens.label().equals(label))
                .findFirst().orElse(null));
    }
}

package com.rutgerva.aoc2023.day16.utils;

import com.rutgerva.aoc2023.day16.enums.Tile;
import com.rutgerva.aoc2023.day16.enums.TravellingDirection;
import static com.rutgerva.aoc2023.day16.enums.TravellingDirection.DOWN;
import static com.rutgerva.aoc2023.day16.enums.TravellingDirection.LEFT;
import static com.rutgerva.aoc2023.day16.enums.TravellingDirection.RIGHT;
import static com.rutgerva.aoc2023.day16.enums.TravellingDirection.UP;
import com.rutgerva.aoc2023.utils.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.List;

public final class DaySixteenUtils {

    private static char[][] floor;
    private static boolean[][] traveled;

    private static List<ImmutablePair<int[], TravellingDirection>> hasBeenTraversedInDirection;

    private DaySixteenUtils() {
    }

    public static void initialize(List<String> input) {
        floor = StringUtils.listToGrid(input);
        traveled = new boolean[floor.length][floor[0].length];
        hasBeenTraversedInDirection = new ArrayList<>();
    }

    public static char[][] getGrid() {
        return floor;
    }

    public static void traverseGrid(int[] position, TravellingDirection travellingDirection) {
        ImmutablePair key = new ImmutablePair(position, travellingDirection);
        //no more steps if the next position of the beam was going out of bounds
        if (!outOfBounds(position[0], position[1]) && !hasBeenTraversedInDirection(position, travellingDirection)) {
            hasBeenTraversedInDirection.add(key);
            traveled[position[0]][position[1]] = true;
            Tile currentTile = Tile.getTileBySymbol(floor[position[0]][position[1]]);
            TravellingDirection newDirection;
            switch (currentTile) {
                case EMPTY_SPACE -> {
                    newDirection = travellingDirection;
                    traverseGrid(calculateNextPosition(position, newDirection), newDirection);
                }
                case MIRROR_TILT_LEFT -> {
                    if (travellingDirection.equals(LEFT))
                        newDirection = UP;
                    else if (travellingDirection.equals(DOWN))
                        newDirection = RIGHT;
                    else if (travellingDirection.equals(UP))
                        newDirection = LEFT;
                    else
                        newDirection = DOWN;
                    traverseGrid(calculateNextPosition(position, newDirection), newDirection);
                }
                case MIRROR_TILT_RIGHT -> {
                    if (travellingDirection.equals(LEFT))
                        newDirection = DOWN;
                    else if (travellingDirection.equals(DOWN))
                        newDirection = LEFT;
                    else if (travellingDirection.equals(UP))
                        newDirection = RIGHT;
                    else
                        newDirection = UP;
                    traverseGrid(calculateNextPosition(position, newDirection), newDirection);
                }
                case HSPLIT -> {
                    if (travellingDirection.equals(UP) || travellingDirection.equals(DOWN)) {
                        traverseGrid(calculateNextPosition(position, LEFT), LEFT);
                        traverseGrid(calculateNextPosition(position, RIGHT), RIGHT);
                    } else {
                        newDirection = travellingDirection;
                        traverseGrid(calculateNextPosition(position, newDirection), newDirection);
                    }
                }
                case VSPLIT -> {
                    if (travellingDirection.equals(LEFT) || travellingDirection.equals(RIGHT)) {
                        traverseGrid(calculateNextPosition(position, UP), UP);
                        traverseGrid(calculateNextPosition(position, DOWN), DOWN);
                    } else {
                        newDirection = travellingDirection;
                        traverseGrid(calculateNextPosition(position, newDirection), newDirection);
                    }
                }
                case INVALID -> throw new RuntimeException("Something went wrong, unknown symbol encountered");
            }
        }
    }

    private static int[] calculateNextPosition(int[] currentPosition, TravellingDirection direction) {
        int[] movement = direction.getMovement();
        return new int[]{currentPosition[0] + movement[0], currentPosition[1] + movement[1]};
    }

    private static boolean outOfBounds(int x, int y) {
        return (x < 0 || x > floor.length - 1)
                || (y < 0 || y > floor[0].length - 1);
    }

    private static boolean hasBeenTraversedInDirection(int[] position, TravellingDirection direction) {
        return hasBeenTraversedInDirection
                .stream()
                .anyMatch(pair -> pair.left[0] == position[0] && pair.left[1] == position[1] && direction.equals(pair.right));
    }

    public static int getAmountOfEnergizedTiles() {
        int count = 0;
        for (int i = 0; i < traveled.length; i++) {
            for (int y = 0; y < traveled[0].length; y++) {
                count += traveled[i][y] ? 1 : 0;
            }
        }
        return count;
    }
}

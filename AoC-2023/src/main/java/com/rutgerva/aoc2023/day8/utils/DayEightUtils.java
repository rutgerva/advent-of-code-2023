package com.rutgerva.aoc2023.day8.utils;

import com.rutgerva.aoc2023.day8.models.Node;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DayEightUtils {

    private static String instructions;
    private static List<Node> mapOfNodes;

    private DayEightUtils() {
    }

    public static void initialize(List<String> input) {
        instructions = input.get(0);    //assume first line is always the set of instructions
        buildListOfNodes(input.subList(2, input.size()));
    }

    private static void buildListOfNodes(List<String> listOfNodes) {
        mapOfNodes = new ArrayList<>();
        Pattern pattern = Pattern.compile("([A-Z]{3})\\s*=\\s*\\(([A-Z]{3}),\\s*([A-Z]{3})\\)");
        for (String nodeAsString : listOfNodes) {
            Matcher matcher = pattern.matcher(nodeAsString);
            if (matcher.find()) {
                mapOfNodes.add(new Node(matcher.group(1), new ImmutablePair<>(matcher.group(2), matcher.group(3))));
            }
        }
    }

    public static Integer getAmountOfStepsNeededToReachEnd() {
        Integer steps = 0, index = 0;
        char[] instructionsAsChars = instructions.toCharArray();
        boolean endReached = false;
        String currentNodeAsString = "AAA";
        while (!endReached) {
            Node currentNode = findNodeBasedOnLabel(currentNodeAsString);
            currentNodeAsString = instructionsAsChars[index] == 'L' ? currentNode.navigateLeft() : currentNode.navigateRight();
            if (currentNodeAsString.equals("ZZZ")) {
                endReached = true;
            }
            index = index == instructions.length() - 1 ? 0 : ++index;
            steps++;
        }
        return steps;
    }

    private static Node findNodeBasedOnLabel(final String label) {
        return mapOfNodes.stream()
                .filter(node -> node.getCurrentNodeLabel().equals(label))
                .toList().get(0);
    }
}

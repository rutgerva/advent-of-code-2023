package com.rutgerva.aoc2023.day5.utils;

import com.rutgerva.aoc2023.utils.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DayFiveUtils {
    public static List<String> input = new ArrayList<>();

    public static List<Mapping> seedToSoil = new ArrayList<>();
    public static List<Mapping> soilToFert = new ArrayList<>();
    public static List<Mapping> fertToWater = new ArrayList<>();
    public static List<Mapping> waterToLight = new ArrayList<>();
    public static List<Mapping> lightToTemp = new ArrayList<>();
    public static List<Mapping> tempToHumidity = new ArrayList<>();
    public static List<Mapping> humidityToLocation = new ArrayList<>();

    public static void readInput(String inputFile) throws IOException {
        //Read file
        input = Files.readAllLines(Paths.get("src/main/resources/" + inputFile));
    }

    public static void initializeMappings() {
        processMappings("seed", "soil", seedToSoil);
        processMappings("soil", "fertilizer", soilToFert);
        processMappings("fertilizer", "water", fertToWater);
        processMappings("water", "light", waterToLight);
        processMappings("light", "temperature", lightToTemp);
        processMappings("temperature", "humidity", tempToHumidity);
        processMappings("humidity", "location", humidityToLocation);
    }

    private static void processMappings(String from, String to, List<Mapping> mappingsListToFill) {
        boolean startProcessing = false;
        StringBuilder mappingsTitle = new StringBuilder();
        mappingsTitle.append(from)
                .append("-to-")
                .append(to)
                .append(" map:");
        for (String line: input) {
            //process line if we found the start of the mapping
            if(startProcessing) {
                if(line.trim().isEmpty()) {
                    break;
                } else {
                    List<Long> mapping = StringUtils.extractListOfNumbersFromString(line);
                    mappingsListToFill.add(new Mapping(mapping.get(0), mapping.get(1), mapping.get(2)));
                }
            }
            //enable processing when we found the beginning of the mapping
            if(line.trim().contentEquals(mappingsTitle))
                startProcessing = true;
        }
    }

    public static List<Long> getSeedList() {
        //given that seed list is on the first line
        return StringUtils.extractListOfNumbersFromString(input.get(0));
    }

    public static Long mapSourceToDestination(Long toMap, List<Mapping> mappingsList) {
        for (Mapping mapping : mappingsList) {
            if (mapping.isSourceInMapping(toMap)) {
                return mapping.mapping(toMap);
            }
        }
        return toMap;
    }
}

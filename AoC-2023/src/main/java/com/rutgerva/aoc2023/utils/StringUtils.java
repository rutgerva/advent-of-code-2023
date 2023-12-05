package com.rutgerva.aoc2023.utils;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private StringUtils() {

    }


    public static List<Long> extractListOfNumbersFromString(String line) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(line);

        return matcher.results()
                .map(MatchResult::group)
                .mapToLong(Long::parseLong)
                .boxed()
                .toList();
    }
}

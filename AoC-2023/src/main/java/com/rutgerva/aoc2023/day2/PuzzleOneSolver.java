package com.rutgerva.aoc2023.day2;

import com.rutgerva.aoc2023.PuzzleSolver;
import com.rutgerva.aoc2023.day2.utils.DayTwoUtils;
import static java.lang.Boolean.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PuzzleOneSolver extends PuzzleSolver {

    public PuzzleOneSolver(String inputFile) {
        super(inputFile);
    }

    public Integer solve() {
        BufferedReader bufferedReader = this.getBufferedReader();
        int result = 0;
        try {
            String line = bufferedReader.readLine();
            while (null != line) {
                String[] setsInGame = extractSetsInGame(line);
                if(Boolean.TRUE.equals(checkGame(setsInGame))) {
                    result += extractGameId(line);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Boolean checkGame(String[] gameSets) {
        for(String set: gameSets) {
            if(Boolean.FALSE.equals(DayTwoUtils.isValidSet(set)))
                return FALSE;
        }
        return TRUE;
    }

    private String[] extractSetsInGame(String gameLine) {
        return gameLine
                .split(":")[1]
                .split(";");
    }

    private Integer extractGameId(String gameLine) {
        Pattern pattern = Pattern.compile("Game (\\d+)");
        Matcher matcher = pattern.matcher(gameLine);
        Integer gameId = 0;

        if (matcher.find())
            gameId = Integer.parseInt(matcher.group(1));

        return gameId;
    }
}

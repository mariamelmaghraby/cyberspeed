package org.example;

import java.util.*;

public class WinChecker {
    private GameConfig config;

    public WinChecker(GameConfig config) {
        this.config = config;
    }

    public List<String> checkWins(String[][] matrix) {
        List<String> wins = new ArrayList<>();

        for (Map.Entry<String, WinCombination> entry : config.winCombinations.entrySet()) {
            String winName = entry.getKey();
            WinCombination combination = entry.getValue();

            if (combination.when.equals("same_symbols")) {
                if (checkSameSymbols(matrix, combination.count)) {
                    wins.add(winName);
                }
            }
            // Add other checks (linear_symbols, etc.) here
        }
        return wins;
    }

    private boolean checkSameSymbols(String[][] matrix, int count) {
        Map<String, Integer> symbolCounts = new HashMap<>();
        for (String[] row : matrix) {
            for (String symbol : row) {
                symbolCounts.put(symbol, symbolCounts.getOrDefault(symbol, 0) + 1);
                if (symbolCounts.get(symbol) >= count) {
                    return true;
                }
            }
        }
        return false;
    }
}


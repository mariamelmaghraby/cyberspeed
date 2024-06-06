package org.example;

import java.util.*;

public class MatrixGenerator {
    private GameConfig config;

    public MatrixGenerator(GameConfig config) {
        this.config = config;
    }

    public String[][] generateMatrix() {
        String[][] matrix = new String[config.rows][config.columns];
        Random random = new Random();

        List<SymbolProbability> standardSymbolsList = config.probabilities.standardSymbols;
        int symbolIndex = 0;

        for (int row = 0; row < config.rows; row++) {
            for (int col = 0; col < config.columns; col++) {
                // Get the symbol probabilities for the current position
                Map<String, Integer> symbolProbabilities = standardSymbolsList.get(symbolIndex++).symbols;
                // Generate a random symbol based on the probabilities
                matrix[row][col] = getRandomSymbol(symbolProbabilities, random);
            }
        }
        return matrix;
    }

    private String getRandomSymbol(Map<String, Integer> symbolProbabilities, Random random) {
        int sum = symbolProbabilities.values().stream().mapToInt(Integer::intValue).sum();
        int rand = random.nextInt(sum);
        int cumulative = 0;
        for (Map.Entry<String, Integer> entry : symbolProbabilities.entrySet()) {
            cumulative += entry.getValue();
            if (rand < cumulative) {
                return entry.getKey();
            }
        }
        return null; // should never reach here if probabilities are set correctly
    }
}


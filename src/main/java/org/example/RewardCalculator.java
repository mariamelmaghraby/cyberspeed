package org.example;

import java.util.List;

public class RewardCalculator {
    private GameConfig config;

    public RewardCalculator(GameConfig config) {
        this.config = config;
    }

    public double calculateReward(double betAmount, List<String> wins, String[][] matrix) {
        double reward = betAmount;

        for (String win : wins) {
            reward *= config.winCombinations.get(win).rewardMultiplier;
        }

        for (String[] row : matrix) {
            for (String symbol : row) {
                Symbol sym = config.symbols.get(symbol);
                if (sym.type.equals("bonus")) {
                    if (sym.impact.equals("multiply_reward")) {
                        reward *= sym.rewardMultiplier;
                    } else if (sym.impact.equals("extra_bonus")) {
                        reward += sym.extra;
                    }
                }
            }
        }
        return reward;
    }
}

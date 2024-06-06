package org.example;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
//    public static void main(String[] args) {
//        System.out.println("Hello world!");
//    }

    public static void main(String[] args) {


        String configFilePath = "src/main/resources/config.json";
        double betAmount = 100;

        try {
            GameConfig config = GameConfig.loadConfig(configFilePath);
            MatrixGenerator matrixGenerator = new MatrixGenerator(config);
            String[][] matrix = matrixGenerator.generateMatrix();

            WinChecker winChecker = new WinChecker(config);
            List<String> wins = winChecker.checkWins(matrix);

            RewardCalculator rewardCalculator = new RewardCalculator(config);
            double finalReward = rewardCalculator.calculateReward(betAmount, wins, matrix);

            // Output the result as JSON
            System.out.println("Matrix: " + Arrays.deepToString(matrix));
            System.out.println("Wins: " + wins);
            System.out.println("Final Reward: " + finalReward);
        } catch (IOException e) {
            System.err.println("Failed to load configuration: " + e.getMessage());
        }
    }
}
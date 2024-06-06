package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GameConfig {
    public int columns;
    public int rows;
    public Map<String, Symbol> symbols;
    public Probabilities probabilities;
    public Map<String, WinCombination> winCombinations;

    public static GameConfig loadConfig(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), GameConfig.class);
    }
}

class Symbol {
    @JsonProperty("reward_multiplier")
    public double rewardMultiplier;
    public String type;
    public String impact;
    public int extra;
}

class Probabilities {
    @JsonProperty("standard_symbols")
    public List<SymbolProbability> standardSymbols;
    @JsonProperty("bonus_symbols")
    public SymbolProbability bonusSymbols;
}

class SymbolProbability {
    public Map<String, Integer> symbols;
}

class WinCombination {
    public double rewardMultiplier;
    public String when;
    public int count;
    public String group;
    public String[][] coveredAreas;
}

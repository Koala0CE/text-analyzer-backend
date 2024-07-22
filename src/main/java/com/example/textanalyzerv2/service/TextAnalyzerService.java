package com.example.textanalyzerv2.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class TextAnalyzerService {
    
    public Map<String, Integer> analyzeText(String text, boolean isVowels) {
        return isVowels ? analyzeVowels(text) : analyzeConsonants(text);
    }

    private Map<String, Integer> analyzeVowels(String text) {
        Map<String, Integer> vowelCounts = new HashMap<>();
        vowelCounts.put("A", 0);
        vowelCounts.put("E", 0);
        vowelCounts.put("I", 0);
        vowelCounts.put("O", 0);
        vowelCounts.put("U", 0);

        for (char c : text.toUpperCase().toCharArray()) {
            vowelCounts.computeIfPresent(String.valueOf(c), (k, v) -> v + 1);
        }

        return vowelCounts;
    }

    private Map<String, Integer> analyzeConsonants(String text) {
        Map<String, Integer> consonantCounts = new HashMap<>();

        for (char c : text.toUpperCase().toCharArray()) {
            if (Character.isLetter(c) && !isVowel(c)) {
                consonantCounts.merge(String.valueOf(c), 1, Integer::sum);
            }
        }

        return consonantCounts;
    }

    private boolean isVowel(char c) {
        return "AEIOU".indexOf(c) != -1;
    }
}

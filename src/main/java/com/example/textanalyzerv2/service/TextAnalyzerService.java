package com.example.textanalyzerv2.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class TextAnalyzerService {

    // Constants for vowels
    private static final String VOWELS = "AEIOU";

    /**
     * Analyzes the given text and returns a map of character counts.
     * @param text The text to be analyzed.
     * @param isVowels If true, analyze vowels; otherwise, analyze consonants.
     * @return A map with character counts.
     */
    public Map<String, Integer> analyzeText(String text, boolean isVowels) {
        return isVowels ? analyzeVowels(text) : analyzeConsonants(text);
    }

    /**
     * Analyzes the vowels in the given text.
     * @param text The text to be analyzed.
     * @return A map with vowel counts.
     */
    private Map<String, Integer> analyzeVowels(String text) {
        Map<String, Integer> vowelCounts = initializeVowelMap();

        for (char c : text.toUpperCase().toCharArray()) {
            vowelCounts.computeIfPresent(String.valueOf(c), (k, v) -> v + 1);
        }

        return vowelCounts;
    }

    /**
     * Analyzes the consonants in the given text.
     * @param text The text to be analyzed.
     * @return A map with consonant counts.
     */
    private Map<String, Integer> analyzeConsonants(String text) {
        Map<String, Integer> consonantCounts = new HashMap<>();

        for (char c : text.toUpperCase().toCharArray()) {
            if (Character.isLetter(c) && !isVowel(c)) {
                consonantCounts.merge(String.valueOf(c), 1, Integer::sum);
            }
        }

        return consonantCounts;
    }

    /**
     * Checks if a character is a vowel.
     * @param c The character to check.
     * @return True if the character is a vowel, false otherwise.
     */
    private boolean isVowel(char c) {
        return VOWELS.indexOf(c) != -1;
    }

    /**
     * Initializes a map with vowel keys set to 0.
     * @return A map with vowels initialized to 0.
     */
    private Map<String, Integer> initializeVowelMap() {
        Map<String, Integer> vowelCounts = new HashMap<>();
        for (char c : VOWELS.toCharArray()) {
            vowelCounts.put(String.valueOf(c), 0);
        }
        return vowelCounts;
    }
}

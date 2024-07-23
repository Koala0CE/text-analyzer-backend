package com.example.textanalyzerv2.controller;


import com.example.textanalyzerv2.service.TextAnalyzerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TextAnalyzerController {

    private final TextAnalyzerService textAnalyzerService;

    @GetMapping("/analyze")
    public Map<String, Integer> analyzeText(@RequestParam String text,@RequestParam boolean isVowels) {
        return textAnalyzerService.analyzeText(text, isVowels);
    }
}

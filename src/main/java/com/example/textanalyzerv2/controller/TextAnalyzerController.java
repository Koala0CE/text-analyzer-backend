package com.example.textanalyzerv2.controller;


import com.example.textanalyzerv2.model.TextAnalysisRequest;
import com.example.textanalyzerv2.service.TextAnalyzerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TextAnalyzerController {

    private final TextAnalyzerService textAnalyzerService;

    @PostMapping("/analyze")
    public Map<String, Integer> analyzeText(@RequestBody TextAnalysisRequest request) {
        return textAnalyzerService.analyzeText(request.getText(), request.isVowels());
    }
}

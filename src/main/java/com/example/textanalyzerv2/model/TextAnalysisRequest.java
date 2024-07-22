package com.example.textanalyzerv2.model;

import lombok.Data;

@Data
public class TextAnalysisRequest {
    private String text;
    private boolean isVowels;
}
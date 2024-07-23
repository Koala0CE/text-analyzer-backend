package com.example.textanalyzerv2;

import com.example.textanalyzerv2.service.TextAnalyzerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class TextAnalyzerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TextAnalyzerService textAnalyzerService; // Ensure service is available for testing

    @Test
    public void testAnalyzeText_Vowels() throws Exception {
        // Prepare parameters
        String text = "Hello Worlds";
        boolean isVowels = true;


        // Perform the request and verify the result
        mockMvc.perform(MockMvcRequestBuilders.get("/api/analyze")
                        .param("text", text)
                        .param("isVowels", String.valueOf(isVowels))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> {
                    String response = result.getResponse().getContentAsString();
                    Map<String, Integer> actualResponse = objectMapper.readValue(response, Map.class);

                    // Example expected result (adjust based on whether you are testing vowels or consonants)
                    Map<String, Integer> expectedVowelResponse = Map.of(
                            "A", 0,
                            "E", 1,
                            "U", 0,
                            "I", 0,
                            "O", 2
                    );

                    // Assert that the response matches the expected response
                    assertEquals(expectedVowelResponse, actualResponse);
                });
    }

    @Test
    public void testAnalyzeText_Consonants() throws Exception {
        // Prepare parameters
        String text = "Hello Worlds";
        boolean isVowels = false;

        // Perform the request and verify the result
        mockMvc.perform(MockMvcRequestBuilders.get("/api/analyze")
                        .param("text", text)
                        .param("isVowels", String.valueOf(isVowels))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> {
                    // Validate the response content
                    String response = result.getResponse().getContentAsString();
                    Map<String, Integer> actualResponse = objectMapper.readValue(response, Map.class);

                    // Expected result for consonants
                    Map<String, Integer> expectedConsonantResponse = Map.of(
                            "R", 1,
                            "S", 1,
                            "D", 1,
                            "W", 1,
                            "H", 1,
                            "L", 3
                    );

                    // Assert that the response matches the expected response
                    assertEquals(expectedConsonantResponse, actualResponse);

                });
    }
}

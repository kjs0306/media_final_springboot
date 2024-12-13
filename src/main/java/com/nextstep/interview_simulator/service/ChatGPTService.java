package com.nextstep.interview_simulator.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class ChatGPTService {

    private static final String API_URL = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "비공개처리, 차후 환경변수로 떼어놓을예정입니다"; 


    private final RestTemplate restTemplate;

    public ChatGPTService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getInterviewQuestion(String resumeContent) {
        String jsonBody = String.format("{\"model\": \"gpt-4\", \"prompt\": \"%s\", \"max_tokens\": 150}", resumeContent);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);

        JsonObject responseBody = JsonParser.parseString(response.getBody()).getAsJsonObject();
        return responseBody.getAsJsonArray("choices").get(0).getAsJsonObject().get("text").getAsString();
    }
}
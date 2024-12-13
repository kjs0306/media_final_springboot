package com.nextstep.interview_simulator.dto;

public class InterviewResponse {

    private String question;

    public InterviewResponse() {
    }

    public InterviewResponse(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
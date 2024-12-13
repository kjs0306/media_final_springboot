package com.nextstep.interview_simulator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextstep.interview_simulator.dto.AnswerDTO;
import com.nextstep.interview_simulator.dto.InterviewResponse;
import com.nextstep.interview_simulator.service.InterviewService;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping("/{interviewId}/answer")
    public ResponseEntity<?> submitAnswer(@PathVariable Long interviewId, @RequestBody AnswerDTO answerDTO) {
        
        interviewService.saveAnswer(interviewId, answerDTO.getAnswer());

        
        String nextQuestion = interviewService.getNextQuestion(interviewId);
        return ResponseEntity.ok(new InterviewResponse(nextQuestion));
    }
    
    @PostMapping("/{interviewId}/next-question")
    public ResponseEntity<String> getNextQuestion(@PathVariable Long interviewId) {
        String nextQuestion = interviewService.getNextQuestion(interviewId);
        return ResponseEntity.ok(nextQuestion);
    }
}
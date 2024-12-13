package com.nextstep.interview_simulator.dto;

import java.util.ArrayList;
import java.util.List;
import com.nextstep.interview_simulator.domain.InterviewResult;

import com.nextstep.interview_simulator.domain.Interview;

public class InterviewSession {
    private static long idCounter = 1;
    
    private final long id;
    private final Long resumeId; 
    private String currentQuestion;
    private int currentSequence = 0; 
    private List<String> answers = new ArrayList<>();
    private InterviewResult result;
    private Interview interview;  

    public InterviewSession(Long resumeId) {
        this.id = idCounter++;
        this.resumeId = resumeId;
    }

    public static long getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(long idCounter) {
		InterviewSession.idCounter = idCounter;
	}

	

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	public long getId() {
        return id;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public String getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(String currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
    
    public int getCurrentSequence() {
        return currentSequence;
    }

    public void setCurrentSequence(int currentSequence) {
        this.currentSequence = currentSequence;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void addAnswer(String answer) {
        this.answers.add(answer);
    }

    public InterviewResult getResult() {
        return result;
    }

    public void setResult(InterviewResult result) {
        this.result = result;
    }
}
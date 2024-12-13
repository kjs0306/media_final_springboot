package com.nextstep.interview_simulator.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class InterviewResult {

    @Id
    private Long id;
    
    private int score;  
    private Interview interview; 

    public InterviewResult() {
    }

    public InterviewResult(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}
}
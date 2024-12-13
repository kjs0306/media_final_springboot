package com.nextstep.interview_simulator.domain;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class InterviewAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interviewId;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private InterviewQuestion question;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String answerText;

    private Float score;

	public Long getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(Long interviewId) {
		this.interviewId = interviewId;
	}

	public InterviewQuestion getQuestion() {
		return question;
	}

	public void setQuestion(InterviewQuestion question) {
		this.question = question;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

    
}
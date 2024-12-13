package com.nextstep.interview_simulator.dto;

import org.springframework.data.annotation.Id;

import com.nextstep.interview_simulator.domain.Interview;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class InterviewResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;

    // Interview와 1:1
    @OneToOne
    @JoinColumn(name = "interview_id")
    private Interview interview;

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
package com.nextstep.interview_simulator.domain;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.annotation.Id;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Optional<User> user;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Column(nullable = false)
    private String status = "진행 중";

    @Column(columnDefinition = "TEXT")
    private String currentQuestion;

    // InterviewResult와 1:1 관계
    @OneToOne(mappedBy = "interview", cascade = CascadeType.ALL)
    private InterviewResult result;  // InterviewResult 객체를 Interview에 포함

    // Getter 및 Setter
    public InterviewResult getResult() {
        return result;
    }

    public void setResult(InterviewResult result) {
        this.result = result;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Optional<User> getUser() {
		return user;
	}

	public void setUser(Optional<User> user2) {
		this.user = user2;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCurrentQuestion() {
		return currentQuestion;
	}

	public void setCurrentQuestion(String currentQuestion) {
		this.currentQuestion = currentQuestion;
	}

	@Override
	public String toString() {
		return "Interview [id=" + id + ", user=" + user + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", status=" + status + ", currentQuestion=" + currentQuestion + ", result=" + result + "]";
	}

    
}
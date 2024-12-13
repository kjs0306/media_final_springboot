package com.nextstep.interview_simulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nextstep.interview_simulator.domain.Interview;
import com.nextstep.interview_simulator.domain.InterviewSession;

@Repository
public interface InterviewRepository extends JpaRepository<InterviewSession, Long> {

	void save(Interview interview);
}
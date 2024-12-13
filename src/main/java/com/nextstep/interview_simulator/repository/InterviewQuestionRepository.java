package com.nextstep.interview_simulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nextstep.interview_simulator.domain.InterviewQuestion;

@Repository
public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestion, Long> {
    // 인터뷰 질문과 관련된 추가적인 DB 쿼리 작성
}
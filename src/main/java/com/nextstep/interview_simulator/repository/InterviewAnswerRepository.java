package com.nextstep.interview_simulator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nextstep.interview_simulator.domain.InterviewAnswer;

@Repository
public interface InterviewAnswerRepository extends JpaRepository<InterviewAnswer, Long> {
    List<InterviewAnswer> findByQuestionId(Long questionId); // 특정 질문에 대한 모든 답변을 찾기
    List<InterviewAnswer> findByInterviewId(Long interviewId); // 특정 인터뷰의 모든 답변을 찾기
}
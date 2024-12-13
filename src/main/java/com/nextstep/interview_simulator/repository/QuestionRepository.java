package com.nextstep.interview_simulator.repository;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    // 첫 번째 질문 가져오기
    @Query("SELECT q.content FROM Question q WHERE q.resumeId = :resumeId ORDER BY q.sequence ASC LIMIT 1")
    String getFirstQuestionByResumeId(@Param("resumeId") Long resumeId);

    // 다음 질문 가져오기
    String getNextQuestion(Long resumeId);

    // 새로 추가된 다음 질문 가져오기
    @Query("SELECT q.content FROM Question q WHERE q.resumeId = :resumeId AND q.sequence > :currentSequence ORDER BY q.sequence ASC LIMIT 1")
    String getNextQuestionBySequence(@Param("resumeId") Long resumeId, @Param("currentSequence") int currentSequence);
}
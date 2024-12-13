package com.nextstep.interview_simulator.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nextstep.interview_simulator.domain.Interview;
import com.nextstep.interview_simulator.domain.InterviewAnswer;
import com.nextstep.interview_simulator.domain.InterviewResult;
import com.nextstep.interview_simulator.domain.User;
import com.nextstep.interview_simulator.dto.InterviewSession;
import com.nextstep.interview_simulator.repository.InterviewAnswerRepository;
import com.nextstep.interview_simulator.repository.InterviewQuestionRepository;
import com.nextstep.interview_simulator.repository.InterviewRepository;
import com.nextstep.interview_simulator.repository.QuestionRepository;
import com.nextstep.interview_simulator.repository.UserRepository;

@Service
public class InterviewService {

	private final InterviewRepository interviewRepository;
	private final InterviewQuestionRepository interviewQuestionRepository;
	private final InterviewAnswerRepository interviewAnswerRepository;
	private final QuestionRepository questionRepository;
	private final UserRepository userRepository;

	private final Map<Long, InterviewSession> activeInterviews = new HashMap<>();

	public InterviewService(InterviewRepository interviewRepository,
			InterviewQuestionRepository interviewQuestionRepository,
			InterviewAnswerRepository interviewAnswerRepository, QuestionRepository questionRepository,
			UserRepository userRepository) { // UserRepository 추가
		this.interviewRepository = interviewRepository;
		this.interviewQuestionRepository = interviewQuestionRepository;
		this.interviewAnswerRepository = interviewAnswerRepository;
		this.questionRepository = questionRepository;
		this.userRepository = userRepository; // userRepository 초기화
	}

	// 인터뷰 시작: 사용자의 이력서를 바탕으로 첫 번째 질문
	public InterviewSession startInterview(Long resumeId) {
		InterviewSession session = new InterviewSession(resumeId);
		activeInterviews.put(session.getId(), session);

		String firstQuestion = questionRepository.getFirstQuestionByResumeId(resumeId);
		session.setCurrentQuestion(firstQuestion);

		Optional<User> user = userRepository.findByResumeId(resumeId); // UserRepository에서 사용자 조회
		if (user == null) {
			throw new IllegalArgumentException("해당 이력서를 가진 사용자를 찾을 수 없습니다.");
		}

		Interview interview = new Interview();
		interview.setStartTime(LocalDateTime.now()); // 인터뷰 시작 시간
		interview.setStatus("진행 중");
		interview.setCurrentQuestion(firstQuestion); // 첫 번째 질문 저장
		interview.setUser(user); // 사용자 설정

		interviewRepository.save(interview);

		return session;
	}

	// 다음 질문 가져오기
	public String getNextQuestion(Long interviewId) {
		InterviewSession session = activeInterviews.get(interviewId);
		if (session == null) {
			throw new IllegalArgumentException("해당 인터뷰 세션이 존재하지 않습니다.");
		}

		String nextQuestion = questionRepository.getNextQuestion(session.getResumeId());
		if (nextQuestion == null) {
			throw new IllegalStateException("다음 질문을 가져올 수 없습니다.");
		}

		session.setCurrentQuestion(nextQuestion);
		
		com.nextstep.interview_simulator.domain.InterviewSession interview = interviewRepository.findById(interviewId)
				.orElseThrow(() -> new IllegalArgumentException("인터뷰 세션을 찾을 수 없습니다."));
		interview.setCurrentQuestion(nextQuestion);
		interviewRepository.save(interview);

		return nextQuestion; 
	}

	// 사용자가 제출한 답변을 DB에 저장하고, 다음 질문을 반환
	public String saveAnswer(Long interviewId, String answer) {
	    InterviewSession session = activeInterviews.get(interviewId);
	    if (session == null) {
	        throw new IllegalArgumentException("인터뷰 세션이 존재하지 않습니다.");
	    }

	    session.addAnswer(answer);

	    InterviewAnswer interviewAnswer = new InterviewAnswer();
	    interviewAnswer.setInterviewId(interviewId);
	    interviewAnswer.setAnswerText(answer);
	    interviewAnswerRepository.save(interviewAnswer);

	    String nextQuestion = questionRepository.getNextQuestion(session.getResumeId());
	    session.setCurrentQuestion(nextQuestion);

	    Interview interview = session.getInterview();  
	    interview.setCurrentQuestion(nextQuestion);    
	    interviewRepository.save(interview);          

	    return nextQuestion;
	}

	public InterviewResult endInterview(Long interviewId) {
	    InterviewSession session = activeInterviews.remove(interviewId);
	    if (session == null) {
	        throw new IllegalArgumentException("인터뷰 세션이 존재하지 않습니다.");
	    }

	    InterviewResult result = evaluateAnswers(session.getAnswers());
	    session.setResult(result);

	    Interview interview = session.getInterview();
	    if (interview == null) {
	        throw new IllegalArgumentException("인터뷰 객체가 세션에 연결되어 있지 않습니다.");
	    }

	    result.setInterview(interview);
	    interview.setResult(result);  

	    interviewRepository.save(interview);

	    return result;
	}

	// 답변 평가 (미완)
	private InterviewResult evaluateAnswers(List<String> answers) {
		int score = 0;

		for (String answer : answers) {
			if (answer.length() > 50) {
				score += 10;
			} else {
				score += 5;
			}
		}

		// 평가 결과 반환
		return new InterviewResult(score);
	}
}

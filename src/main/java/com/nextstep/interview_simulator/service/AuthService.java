package com.nextstep.interview_simulator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextstep.interview_simulator.domain.User;
import com.nextstep.interview_simulator.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    // 회원가입 기능
    public void registerUser(String email, String password, String name) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);

        userRepository.save(user);
    }

    // 로그인 기능 (세션 사용)
    public void login(String email, String password, HttpServletRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if (!user.getPassword().equals(password)) {  
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        HttpSession session = request.getSession(true); 
        session.setAttribute("user", user); 
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); 
        }
    }
}

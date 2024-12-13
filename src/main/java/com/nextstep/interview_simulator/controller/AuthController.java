package com.nextstep.interview_simulator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextstep.interview_simulator.dto.LoginRequest;
import com.nextstep.interview_simulator.dto.RegisterRequest;
import com.nextstep.interview_simulator.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        authService.login(loginRequest.getEmail(), loginRequest.getPassword(), request);
        return ResponseEntity.ok("로그인 성공");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        authService.registerUser(registerRequest.getEmail(), registerRequest.getPassword(), registerRequest.getName());
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        authService.logout(request);
        return ResponseEntity.ok("로그아웃 성공");
    }
}

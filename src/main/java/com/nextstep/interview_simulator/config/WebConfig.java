package com.nextstep.interview_simulator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // `/api/`로 시작하는 모든 경로에 대해 CORS 허용
                .allowedOrigins("http://localhost:3000")  // React 앱의 주소
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true);  // 쿠키나 인증 정보 포함된 요청 허용
    }
}
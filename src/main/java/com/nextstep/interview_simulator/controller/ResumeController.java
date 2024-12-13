package com.nextstep.interview_simulator.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextstep.interview_simulator.domain.Resume;
import com.nextstep.interview_simulator.service.ResumeService;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping
    public ResponseEntity<Resume> createResume(@RequestBody Resume resume) {
        return ResponseEntity.ok(resumeService.createResume(resume));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Resume>> getResumesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(resumeService.getResumesByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resume> updateResume(@PathVariable Long id, @RequestBody Resume updatedResume) {
        return ResponseEntity.ok(resumeService.updateResume(id, updatedResume));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable Long id) {
        resumeService.deleteResume(id);
        return ResponseEntity.noContent().build();
    }
}
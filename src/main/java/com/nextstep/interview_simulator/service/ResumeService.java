package com.nextstep.interview_simulator.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nextstep.interview_simulator.domain.Resume;
import com.nextstep.interview_simulator.repository.ResumeRepository;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;

    public ResumeService(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public Resume createResume(Resume resume) {
        resume.setCreatedAt(LocalDateTime.now());
        return resumeRepository.save(resume);
    }

    public List<Resume> getResumesByUserId(Long userId) {
        return resumeRepository.findByUserId(userId);
    }

    public Resume updateResume(Long id, Resume updatedResume) {
        Resume resume = resumeRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Resume not found"));
        resume.setTitle(updatedResume.getTitle());
        resume.setContent(updatedResume.getContent());
        resume.setUpdatedAt(LocalDateTime.now());
        return resumeRepository.save(resume);
    }

    public void deleteResume(Long id) {
        resumeRepository.deleteById(id);
    }
}
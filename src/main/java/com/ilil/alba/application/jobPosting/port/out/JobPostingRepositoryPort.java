package com.ilil.alba.application.jobPosting.port.out;

import com.ilil.alba.domain.jobPosting.entity.JobPosting;

import java.util.List;
import java.util.Optional;

public interface JobPostingRepositoryPort {
    JobPosting save(JobPosting jobPosting);
    Optional<JobPosting> findById(Long id);
    List<JobPosting> findAll();
    void delete(JobPosting jobPosting);
}

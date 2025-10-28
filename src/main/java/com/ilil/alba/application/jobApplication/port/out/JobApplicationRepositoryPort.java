package com.ilil.alba.application.jobApplication.port.out;

import com.ilil.alba.domain.jobApplication.entity.JobApplication;

import java.util.Optional;

public interface JobApplicationRepositoryPort {
    JobApplication save(JobApplication jobApplication);
    Optional<JobApplication> findById(Long id);
    void delete(JobApplication jobApplication);
}


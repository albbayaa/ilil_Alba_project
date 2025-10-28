package com.ilil.alba.adapter.out.persistence;

import com.ilil.alba.application.jobApplication.port.out.JobApplicationRepositoryPort;
import com.ilil.alba.domain.jobApplication.entity.JobApplication;
import com.ilil.alba.adapter.out.persistence.jobApplication.JobApplicationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JobApplicationJpaAdapter implements JobApplicationRepositoryPort {

    private final JobApplicationJpaRepository jobApplicationJpaRepository;

    @Override
    public JobApplication save(JobApplication jobApplication) {
        return jobApplicationJpaRepository.save(jobApplication);
    }

    @Override
    public Optional<JobApplication> findById(Long id) {
        return jobApplicationJpaRepository.findById(id);
    }

    @Override
    public void delete(JobApplication jobApplication) {
        jobApplicationJpaRepository.delete(jobApplication);
    }
}

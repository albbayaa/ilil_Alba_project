package com.ilil.alba.adapter.out.persistence;

import com.ilil.alba.application.jobPosting.port.out.JobPostingRepositoryPort;
import com.ilil.alba.domain.jobPosting.entity.JobPosting;
import com.ilil.alba.adapter.out.persistence.jobPosting.JobPostingJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JobPostingJpaAdapter implements JobPostingRepositoryPort {

    private final JobPostingJpaRepository jobPostingJpaRepository;

    @Override
    public JobPosting save(JobPosting jobPosting) {
        return jobPostingJpaRepository.save(jobPosting);
    }

    @Override
    public Optional<JobPosting> findById(Long id) {
        return jobPostingJpaRepository.findById(id);
    }

    @Override
    public List<JobPosting> findAll() {
        return jobPostingJpaRepository.findAll();
    }


    @Override
    public void delete(JobPosting jobPosting) {
        jobPostingJpaRepository.delete(jobPosting);
    }
}

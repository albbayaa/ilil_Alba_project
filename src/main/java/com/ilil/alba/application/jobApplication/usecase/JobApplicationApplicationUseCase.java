package com.ilil.alba.application.jobApplication.usecase;

import com.ilil.alba.application.jobApplication.port.in.JobApplicationPort;
import com.ilil.alba.application.jobApplication.port.out.JobApplicationRepositoryPort;
import com.ilil.alba.domain.jobApplication.entity.JobApplication;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JobApplicationApplicationUseCase implements JobApplicationPort {

    private final JobApplicationRepositoryPort jobApplicationRepositoryPort;

    @Override
    public JobApplication applyJobPosting(JobApplication jobApplication) {
        return jobApplicationRepositoryPort.save(jobApplication);
    }
}

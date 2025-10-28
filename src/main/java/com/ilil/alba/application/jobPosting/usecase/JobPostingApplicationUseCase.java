package com.ilil.alba.application.jobPosting.usecase;

import com.ilil.alba.adapter.in.web.jobPosting.request.JobPostingSearchRequest;
import com.ilil.alba.adapter.in.web.jobPosting.response.JobPostingSearchResponse;
import com.ilil.alba.application.jobPosting.port.in.JobPostingPort;
import com.ilil.alba.application.jobPosting.port.out.JobPostingRepositoryPort;
import com.ilil.alba.domain.jobPosting.entity.JobPosting;
import com.ilil.alba.adapter.in.web.jobPosting.response.JobPostingDetailResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class JobPostingApplicationUseCase implements JobPostingPort {

    private final JobPostingRepositoryPort jobPostingRepositoryPort;

    @Override
    public JobPosting postJobPosting(JobPosting jobPosting) {
        return jobPostingRepositoryPort.save(jobPosting);
    }

    @Override
    public Optional<JobPostingDetailResponse> getJobPostingDetail(Long id) {
        return jobPostingRepositoryPort.findById(id)
                .map(this::convertToDetailResponse);
    }

    @Override
    public List<JobPostingSearchResponse> searchJobPostings(JobPostingSearchRequest request) {
        return List.of();
    }

    private JobPostingDetailResponse convertToDetailResponse(JobPosting jobPosting) {
        return JobPostingDetailResponse.builder()
                .jobPostingId(jobPosting.getJobPostingId())
                .build();
    }
}

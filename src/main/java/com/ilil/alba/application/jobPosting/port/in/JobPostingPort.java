package com.ilil.alba.application.jobPosting.port.in;

import com.ilil.alba.adapter.in.web.jobPosting.request.JobPostingSearchRequest;
import com.ilil.alba.adapter.in.web.jobPosting.response.JobPostingDetailResponse;
import com.ilil.alba.adapter.in.web.jobPosting.response.JobPostingSearchResponse;
import com.ilil.alba.domain.jobPosting.entity.JobPosting;

import java.util.List;
import java.util.Optional;

public interface JobPostingPort {
    JobPosting postJobPosting(JobPosting jobPosting);
    Optional<JobPostingDetailResponse> getJobPostingDetail(Long id);
    List<JobPostingSearchResponse> searchJobPostings(JobPostingSearchRequest request);
}

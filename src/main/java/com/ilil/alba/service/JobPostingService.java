package com.ilil.alba.service;

import com.ilil.alba.dto.JobPostingSearchRequest;
import com.ilil.alba.dto.JobPostingSearchResponse;
import com.ilil.alba.repository.jobPosting.JobPostingDslRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobPostingService {

    private final JobPostingDslRepository jobPostingDslRepository;

    public JobPostingSearchResponse search(JobPostingSearchRequest request,Long lastId,int limit){
        log.info("JobPostingService.search");
        return JobPostingSearchResponse.of(jobPostingDslRepository.searchJobPostings(request,lastId,limit));
    }
}

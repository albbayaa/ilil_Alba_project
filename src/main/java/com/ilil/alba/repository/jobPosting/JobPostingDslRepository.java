package com.ilil.alba.repository.jobPosting;

import com.ilil.alba.dto.jobPosting.JobPostingSearchRequest;
import com.ilil.alba.dto.jobPosting.JobPostingSearchResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostingDslRepository {

    List<JobPostingSearchResponse.SearchResults> searchJobPostings(JobPostingSearchRequest request,Long lastId,int limit);
}

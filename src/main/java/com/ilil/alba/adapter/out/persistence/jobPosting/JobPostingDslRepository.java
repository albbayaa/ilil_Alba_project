package com.ilil.alba.adapter.out.persistence.jobPosting;

import com.ilil.alba.adapter.in.web.jobPosting.request.JobPostingSearchRequest;
import com.ilil.alba.adapter.in.web.jobPosting.response.JobPostingSearchResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostingDslRepository {

    List<JobPostingSearchResponse.SearchResults> searchJobPostings(JobPostingSearchRequest request,Long lastId,int limit);
}

package com.ilil.alba.adapter.in.web.jobPosting.controller;

import com.ilil.alba.adapter.in.web.jobPosting.request.JobPostingSearchRequest;
import com.ilil.alba.application.jobPosting.port.in.JobPostingPort;
import com.ilil.alba.common.response.BaseResponse;
import com.ilil.alba.domain.jobPosting.entity.JobPosting;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job_posting")
@RequiredArgsConstructor
public class JobPostingWebController {

    private final JobPostingPort jobPostingPort;

    @PostMapping("/post")
    public BaseResponse<?> postJobPosting(@RequestBody JobPosting jobPosting) {
        return new BaseResponse<>(jobPostingPort.postJobPosting(jobPosting));
    }

    @GetMapping("/detail/{id}")
    public BaseResponse<?> getJobPostingDetail(@PathVariable Long id) {
        return new BaseResponse<>(jobPostingPort.getJobPostingDetail(id)
                .orElseThrow(() -> new RuntimeException("공고를 찾을 수 없습니다.")));
    }

    @GetMapping("/search")
    public BaseResponse<?> searchJobPostings(@RequestBody JobPostingSearchRequest request) {
        return new BaseResponse<>(jobPostingPort.searchJobPostings(request));
    }
}

package com.ilil.alba.controller;


import com.ilil.alba.common.response.BaseResponse;
import com.ilil.alba.domain.base.IsOneDayJob;
import com.ilil.alba.dto.jobPosting.JobPostingDetailResponse;
import com.ilil.alba.dto.jobPosting.JobPostingRequest;
import com.ilil.alba.dto.jobPosting.JobPostingSearchRequest;
import com.ilil.alba.dto.jobPosting.JobPostingSearchResponse;
import com.ilil.alba.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/job_posting")
public class JobPostingController {
    private final JobPostingService jobPostingService;

    @GetMapping("/search")
    public BaseResponse<JobPostingSearchResponse> jobSearch(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location,
            @RequestParam(name = "workDate", required = false)
            @DateTimeFormat(pattern = "yyyyMMdd") LocalDate workDate,
            @RequestParam(required = false) IsOneDayJob isOneDayJob,
            @RequestParam(required = false) Long lastId,
            @RequestParam(defaultValue = "10") Integer limit
    ) {
        JobPostingSearchRequest request = JobPostingSearchRequest.of(title, location, workDate, isOneDayJob);
        return new BaseResponse<>(jobPostingService.search(request, lastId,limit));
    }

    @PostMapping("/post")
    public BaseResponse<Void> post(@RequestBody JobPostingRequest request){
        jobPostingService.post(request);
        return new BaseResponse<>(null);
    }

    @GetMapping("/detail")
    public BaseResponse<JobPostingDetailResponse> jobDetail(@RequestParam Long id){
        JobPostingDetailResponse response = jobPostingService.jobPostingDetail(id);
        return new BaseResponse<>(response);
    }

}

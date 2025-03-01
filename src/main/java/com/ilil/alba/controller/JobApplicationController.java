package com.ilil.alba.controller;

import com.ilil.alba.common.response.BaseResponse;
import com.ilil.alba.dto.jopApplication.JobApplicationRequest;
import com.ilil.alba.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/job_application")
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    @PostMapping("/apply")
    public BaseResponse<Void> apply(@RequestBody JobApplicationRequest request){
        jobApplicationService.apply(request);
        return new  BaseResponse<>(null);
    }
}

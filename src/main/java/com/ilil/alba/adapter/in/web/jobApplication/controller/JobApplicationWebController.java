package com.ilil.alba.adapter.in.web.jobApplication.controller;

import com.ilil.alba.application.jobApplication.port.in.JobApplicationPort;
import com.ilil.alba.common.response.BaseResponse;
import com.ilil.alba.domain.jobApplication.entity.JobApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job_application")
@RequiredArgsConstructor
public class JobApplicationWebController {

    private final JobApplicationPort jobApplicationPort;

    @PostMapping("/apply")
    public BaseResponse<?> applyJobPosting(@RequestBody JobApplication jobApplication) {
        return new BaseResponse<>(jobApplicationPort.applyJobPosting(jobApplication));
    }
}

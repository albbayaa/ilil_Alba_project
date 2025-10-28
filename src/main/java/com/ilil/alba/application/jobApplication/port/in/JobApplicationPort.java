package com.ilil.alba.application.jobApplication.port.in;

import com.ilil.alba.domain.jobApplication.entity.JobApplication;

public interface JobApplicationPort {
    JobApplication applyJobPosting(JobApplication jobApplication);
}

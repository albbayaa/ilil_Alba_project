package com.ilil.alba.adapter.in.web.jobApplication.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class JobApplicationRequest {

    private String applicantName;

    private String contactInfo;

    private String resumeText;

    private Long memberId;

    private Long jobPostingId;

}

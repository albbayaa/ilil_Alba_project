package com.ilil.alba.service;

import com.ilil.alba.common.exception.JobPostingException;
import com.ilil.alba.common.exception.MemberException;
import com.ilil.alba.domain.jobApplication.entity.JobApplication;
import com.ilil.alba.domain.jobPosting.entity.JobPosting;
import com.ilil.alba.domain.member.entity.Member;
import com.ilil.alba.domain.base.BaseStatus;
import com.ilil.alba.dto.jopApplication.JobApplicationRequest;
import com.ilil.alba.repository.jobApplication.JobApplicationJpaRepository;
import com.ilil.alba.repository.jobPosting.JobPostingJpaRepository;
import com.ilil.alba.repository.member.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ilil.alba.common.response.status.BaseExceptionResponseStatus.NOT_FOUND_MEMBER;
import static com.ilil.alba.common.response.status.BaseExceptionResponseStatus.NOT_FOUND_POSTING;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JobApplicationService {
    private final MemberJpaRepository memberJpaRepository;
    private final JobPostingJpaRepository jobPostingJpaRepository;
    private final JobApplicationJpaRepository jobApplicationJpaRepository;


    @Transactional
    public void apply(JobApplicationRequest request){
        Member member = memberJpaRepository.findById(request.getMemberId())
                .orElseThrow(()->new MemberException(NOT_FOUND_MEMBER));

        JobPosting jobPosting = jobPostingJpaRepository.findById(request.getJobPostingId())
                .orElseThrow(()->new JobPostingException(NOT_FOUND_POSTING));

        JobApplication jobApplication = JobApplication.builder()
                .applicantName(request.getApplicantName())
                .contactInfo(request.getContactInfo())
                .resumeText(request.getResumeText())
                .member(member)
                .jobPosting(jobPosting)
                .status(BaseStatus.ACTIVE)
                .build();

        jobApplicationJpaRepository.save(jobApplication);
    }
}

package com.ilil.alba.service;

import com.ilil.alba.common.exception.MemberException;
import com.ilil.alba.domain.JobPosting;
import com.ilil.alba.domain.Member;
import com.ilil.alba.domain.base.BaseStatus;
import com.ilil.alba.dto.JobPostingRequest;
import com.ilil.alba.dto.JobPostingSearchRequest;
import com.ilil.alba.dto.JobPostingSearchResponse;
import com.ilil.alba.repository.jobPosting.JobPostingDslRepository;
import com.ilil.alba.repository.jobPosting.JobPostingJpaRepository;
import com.ilil.alba.repository.member.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ilil.alba.common.response.status.BaseExceptionResponseStatus.NOT_FOUND_MEMBER;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobPostingService {

    private final JobPostingDslRepository jobPostingDslRepository;
    private final JobPostingJpaRepository jobPostingJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    public JobPostingSearchResponse search(JobPostingSearchRequest request,Long lastId,int limit){
        log.info("JobPostingService.search");
        return JobPostingSearchResponse.of(jobPostingDslRepository.searchJobPostings(request,lastId,limit));
    }

    @Transactional
    public void post(JobPostingRequest request){
        log.info("JobPostingService.post");

        Member member = memberJpaRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberException(NOT_FOUND_MEMBER));

        JobPosting jobPosting = JobPosting
                .builder()
                .title(request.getTitle())
                .detail(request.getDetail())
                .location(request.getLocation())
                .workDate(request.getWorkDate())
                .dailyWage(request.getDailyWage())
                .paymentDate(request.getPaymentDate())
                .isOneDayJob(request.isOneDayJob())
                .status(BaseStatus.ACTIVE)
                .member(member)
                .build();

        jobPostingJpaRepository.save(jobPosting);
    }
}

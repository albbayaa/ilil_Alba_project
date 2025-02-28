package com.ilil.alba.service;

import com.ilil.alba.common.exception.JobPostingException;
import com.ilil.alba.common.exception.MemberException;
import com.ilil.alba.domain.JobPosting;
import com.ilil.alba.domain.Member;
import com.ilil.alba.domain.base.BaseStatus;
import com.ilil.alba.dto.jobPosting.JobPostingDetailResponse;
import com.ilil.alba.dto.jobPosting.JobPostingRequest;
import com.ilil.alba.dto.jobPosting.JobPostingSearchRequest;
import com.ilil.alba.dto.jobPosting.JobPostingSearchResponse;
import com.ilil.alba.repository.jobPosting.JobPostingDslRepository;
import com.ilil.alba.repository.jobPosting.JobPostingJpaRepository;
import com.ilil.alba.repository.member.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ilil.alba.common.response.status.BaseExceptionResponseStatus.NOT_FOUND_MEMBER;
import static com.ilil.alba.common.response.status.BaseExceptionResponseStatus.NOT_FOUND_POSTING;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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
                .isOneDayJob(request.getIsOneDayJob())
                .status(BaseStatus.ACTIVE)
                .member(member)
                .build();

        jobPostingJpaRepository.save(jobPosting);
    }

    public JobPostingDetailResponse jobPostingDetail(Long jobPostingId){
        log.info("JobPostingService.jobPostingDetail");

        JobPosting jobPosting = jobPostingJpaRepository.jobPostingDetail(jobPostingId)
                .orElseThrow(()->new JobPostingException(NOT_FOUND_POSTING));

        return JobPostingDetailResponse.from(jobPosting);
    }
}

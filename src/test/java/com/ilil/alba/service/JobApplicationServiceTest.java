package com.ilil.alba.service;

import com.ilil.alba.common.exception.JobPostingException;
import com.ilil.alba.domain.jobPosting.entity.JobPosting;
import com.ilil.alba.domain.member.entity.Member;
import com.ilil.alba.domain.base.BaseStatus;
import com.ilil.alba.domain.base.IsOneDayJob;
import com.ilil.alba.dto.jopApplication.JobApplicationRequest;
import com.ilil.alba.repository.jobApplication.JobApplicationJpaRepository;
import com.ilil.alba.repository.jobPosting.JobPostingJpaRepository;
import com.ilil.alba.repository.member.MemberJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static com.ilil.alba.common.response.status.BaseExceptionResponseStatus.NOT_FOUND_POSTING;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class JobApplicationServiceTest {


    @Mock
    private JobApplicationJpaRepository jobApplicationJpaRepository;

    @Mock
    private JobPostingJpaRepository jobPostingJpaRepository;

    @Mock
    private MemberJpaRepository memberJpaRepository;

    @InjectMocks
    private JobApplicationService jobApplicationService;

    private Member member;
    private JobPosting jobPosting;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        member = Member.builder()
                .memberId(1L)
                .name("테스트 유저")
                .nickname("테스트 닉네임")
                .birthday(LocalDate.of(2000, 1, 1))
                .phoneNumber("010-1234-5678")
                .status(BaseStatus.ACTIVE)
                .build();

        jobPosting = JobPosting.builder()
                .jobPostingId(1L)
                .title("테스트 공고")
                .isOneDayJob(IsOneDayJob.TRUE)
                .workDate(LocalDate.now())
                .dailyWage(BigDecimal.valueOf(2000))
                .paymentDate(LocalDate.now())
                .detail("16시부터 2시간 쉬움")
                .status(BaseStatus.ACTIVE)
                .member(member)
                .build();

    }

    @Test
    void 지원_정상_작동(){
        //given
        JobApplicationRequest request = JobApplicationRequest.builder()
                .applicantName("테스트")
                .contactInfo("테스트")
                .resumeText("테스트")
                .jobPostingId(1L)
                .memberId(1L)
                .build();


        when(memberJpaRepository.findById(1L)).thenReturn(Optional.of(member));
        when(jobPostingJpaRepository.findById(1L)).thenReturn(Optional.of(jobPosting));

        //when
        jobApplicationService.apply(request);

        //then
        verify(jobApplicationJpaRepository, times(1)).save(any());
    }

    @Test
    void 없는_공고에_대한_지원_예외_작동(){
        //given
        JobApplicationRequest request = JobApplicationRequest.builder()
                .applicantName("테스트")
                .contactInfo("테스트")
                .resumeText("테스트")
                .jobPostingId(2L)
                .memberId(1L)
                .build();

        when(memberJpaRepository.findById(1L)).thenReturn(Optional.of(member));

        //when
        JobPostingException exception = assertThrows(JobPostingException.class, ()-> jobApplicationService.apply(request));

        //then
        assertEquals(NOT_FOUND_POSTING, exception.getExceptionStatus());
        verify(jobApplicationJpaRepository, never()).save(any());
    }

}
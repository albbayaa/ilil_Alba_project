package com.ilil.alba.service;

import com.ilil.alba.common.exception.MemberException;
import com.ilil.alba.domain.JobPosting;
import com.ilil.alba.domain.Member;
import com.ilil.alba.domain.base.BaseStatus;
import com.ilil.alba.domain.base.IsOneDayJob;
import com.ilil.alba.dto.jobPosting.JobPostingRequest;
import com.ilil.alba.dto.jobPosting.JobPostingSearchRequest;
import com.ilil.alba.dto.jobPosting.JobPostingSearchResponse;
import com.ilil.alba.repository.jobPosting.JobPostingDslRepository;
import com.ilil.alba.repository.jobPosting.JobPostingJpaRepository;
import com.ilil.alba.repository.member.MemberJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ilil.alba.common.response.status.BaseExceptionResponseStatus.NOT_FOUND_MEMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JobPostingServiceTest {

    @Mock
    private JobPostingDslRepository jobPostingDslRepository;

    @Mock
    private JobPostingJpaRepository jobPostingJpaRepository;

    @Mock
    private MemberJpaRepository memberJpaRepository;

    @InjectMocks
    private JobPostingService jobPostingService;

    private JobPostingSearchRequest request;
    private Member member;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = JobPostingSearchRequest.of("알바", "서울", LocalDate.now(), IsOneDayJob.TRUE);

        member = Member.builder()
                .memberId(1L)
                .name("테스트 유저")
                .nickname("테스트 닉네임")
                .birthday(LocalDate.of(2000, 1, 1))
                .phoneNumber("010-1234-5678")
                .status(BaseStatus.ACTIVE)
                .build();
    }

    @Test
    void 검색_결과가_없는경우_빈리스트반환() {
        // given
        when(jobPostingDslRepository.searchJobPostings(request, null, 10))
                .thenReturn(Collections.emptyList());

        // when
        JobPostingSearchResponse response = jobPostingService.search(request, null, 10);

        // then
        assertEquals(0, response.getResults().size());
        verify(jobPostingDslRepository, times(1)).searchJobPostings(request, null, 10);
    }


    @Test
    void 검색_필터링_정상_작동(){
        //given
        JobPostingSearchRequest request = JobPostingSearchRequest.of("null", "서울", LocalDate.now(), IsOneDayJob.TRUE);

        var searchResult1 = JobPostingSearchResponse.SearchResults.of(1L, "가정집 청소", "서울사", LocalDate.now(), null, null, IsOneDayJob.TRUE);
        var searchResult2 = JobPostingSearchResponse.SearchResults.of(2L, "사무 집기류", "부산", LocalDate.now(), null, null, IsOneDayJob.TRUE);
        var searchResult3 = JobPostingSearchResponse.SearchResults.of(3L, "건대 물건 나르기", "서울시 광진구", LocalDate.now(), null, null, IsOneDayJob.TRUE);
        var searchResult4 = JobPostingSearchResponse.SearchResults.of(4L, "세종대대 물건 나르기", "서울", LocalDate.now(), null, null, IsOneDayJob.TRUE);
        var searchResult5 = JobPostingSearchResponse.SearchResults.of(5L, "카이스트 물건 나르기", "대전", LocalDate.now(), null, null, IsOneDayJob.TRUE);

        List<JobPostingSearchResponse.SearchResults> mockResults = List.of(searchResult1, searchResult2, searchResult3, searchResult4, searchResult5);

        when(jobPostingDslRepository.searchJobPostings(request, null, 10))
                .thenReturn(mockResults.stream()
                        .filter(job -> job.getLocation().contains("서울"))
                        .collect(Collectors.toList()));

        //when
        JobPostingSearchResponse response = jobPostingService.search(request, null, 10);

        //then
        assertThat(response.getResults()).isNotEmpty();
        assertThat(response.getResults()).hasSize(3);
        assertThat(response.getResults().get(0).getLocation()).contains("서울");
    }

    @Test
    void 포스팅_정상_작동(){
        //given
        JobPostingRequest request = JobPostingRequest.of("가정집 청소","일 간단합니다.", "서울사", LocalDate.now(), BigDecimal.valueOf(20000),
                LocalDate.now(), IsOneDayJob.TRUE,1L);

        when(memberJpaRepository.findById(1L)).thenReturn(Optional.of(member));

        //when
        jobPostingService.post(request);

        //then
        verify(jobPostingJpaRepository, times(1)).save(any());
    }

    @Test
    void 공고_작성시_존재하지_않는_멤버_예외_발생(){
        //given
        JobPostingRequest request = JobPostingRequest.of("가정집 청소","일 간단합니다.", "서울사", LocalDate.now(), BigDecimal.valueOf(20000),
                LocalDate.now(), IsOneDayJob.TRUE,2L);

        //ID가 2인 멤버가 없다고 가정하는 것
        when(memberJpaRepository.findById(2L)).thenReturn(Optional.empty());

        //when
        MemberException exception = assertThrows(MemberException.class, ()-> jobPostingService.post(request));

        //then
        assertEquals(NOT_FOUND_MEMBER, exception.getExceptionStatus());
        verify(jobPostingJpaRepository, never()).save(any());
    }

    @Test
    void 상세_보기_정상_작동() {
        // given
        Long jobPostingId = 3L;

        var searchResult1 = JobPosting.builder()
                .jobPostingId(3L)
                .title("세종대 물건 나르기")
                .location("서울시 광진구")
                .workDate(LocalDate.now())
                .dailyWage(BigDecimal.valueOf(120000))
                .paymentDate(LocalDate.now().plusDays(0))
                .isOneDayJob(IsOneDayJob.TRUE)
                .status(BaseStatus.ACTIVE)
                .member(member)
                .build();

        var searchResult2 = JobPosting.builder()
                .jobPostingId(3L)
                .title("서울대 물건 나르기")
                .location("서울시 광진구")
                .workDate(LocalDate.now())
                .dailyWage(BigDecimal.valueOf(110000))
                .paymentDate(LocalDate.now().plusDays(7))
                .isOneDayJob(IsOneDayJob.TRUE)
                .status(BaseStatus.ACTIVE)
                .member(member)
                .build();

        var searchResult3 = JobPosting.builder()
                .jobPostingId(3L)
                .title("건대 물건 나르기")
                .location("서울시 광진구")
                .workDate(LocalDate.now())
                .dailyWage(BigDecimal.valueOf(110000))
                .paymentDate(LocalDate.now().plusDays(7))
                .isOneDayJob(IsOneDayJob.TRUE)
                .status(BaseStatus.ACTIVE)
                .member(member)
                .build();

        when(jobPostingJpaRepository.jobPostingDetail(jobPostingId))
                .thenReturn(Optional.of(searchResult3));

        // when
        Optional<JobPosting> result = jobPostingJpaRepository.jobPostingDetail(jobPostingId);

        // then
        assertTrue(result.isPresent());
        assertEquals(jobPostingId, result.get().getJobPostingId());
    }


}

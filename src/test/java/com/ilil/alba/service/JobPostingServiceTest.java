package com.ilil.alba.service;

import com.ilil.alba.dto.JobPostingSearchRequest;
import com.ilil.alba.dto.JobPostingSearchResponse;
import com.ilil.alba.repository.jobPosting.JobPostingDslRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class JobPostingServiceTest {

    @Mock
    private JobPostingDslRepository jobPostingDslRepository;

    @InjectMocks
    private JobPostingService jobPostingService;

    private JobPostingSearchRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = JobPostingSearchRequest.of("알바", "서울", LocalDate.now(), true);
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
        JobPostingSearchRequest request = JobPostingSearchRequest.of("null", "서울", LocalDate.now(), true);

        var searchResult1 = JobPostingSearchResponse.SearchResults.of(1L, "가정집 청소", "서울사", LocalDate.now(), null, null, true);
        var searchResult2 = JobPostingSearchResponse.SearchResults.of(2L, "사무 집기류", "부산", LocalDate.now(), null, null, true);
        var searchResult3 = JobPostingSearchResponse.SearchResults.of(3L, "건대 물건 나르기", "서울시 광진구", LocalDate.now(), null, null, true);
        var searchResult4 = JobPostingSearchResponse.SearchResults.of(4L, "세종대대 물건 나르기", "서울", LocalDate.now(), null, null, true);
        var searchResult5 = JobPostingSearchResponse.SearchResults.of(5L, "카이스트 물건 나르기", "대전", LocalDate.now(), null, null, true);

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
}

package com.ilil.alba.dto.jobPosting;

import com.ilil.alba.domain.jobPosting.entity.JobPosting;
import com.ilil.alba.domain.base.IsOneDayJob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobPostingSearchResponse {


    private List<SearchResults> results;

    public static JobPostingSearchResponse of(List<SearchResults> results){
        return JobPostingSearchResponse.builder()
                .results(results)
                .build();
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SearchResults{
        private Long jobPostingId;
        private String title;
        private String location;
        private LocalDate workDate;
        private BigDecimal dailyWage;
        private LocalDate paymentDate;
        private IsOneDayJob isOneDayJob;


        public static SearchResults of(Long jobPostingId, String title, String location,
                                                  LocalDate workDate, BigDecimal dailyWage, LocalDate paymentDate,
                                                  IsOneDayJob isOneDayJob){
            return SearchResults.builder()
                    .jobPostingId(jobPostingId)
                    .title(title)
                    .location(location)
                    .workDate(workDate)
                    .dailyWage(dailyWage)
                    .paymentDate(paymentDate)
                    .isOneDayJob(isOneDayJob)
                    .build();
        }

        public static SearchResults from(JobPosting jobPosting){
            return SearchResults.builder()
                    .jobPostingId(jobPosting.getJobPostingId())
                    .title(jobPosting.getTitle())
                    .location(jobPosting.getLocation())
                    .workDate(jobPosting.getWorkDate())
                    .dailyWage(jobPosting.getDailyWage())
                    .paymentDate(jobPosting.getPaymentDate())
                    .isOneDayJob(jobPosting.getIsOneDayJob())
                    .build();
        }

    }

}

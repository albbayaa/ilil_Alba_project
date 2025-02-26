package com.ilil.alba.dto;

import com.ilil.alba.domain.JobPosting;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class JobPostingSearchDTO {

    private Long jobPostingId;
    private String title;
    private String location;
    private LocalDateTime workDate;
    private BigDecimal dailyWage;
    private LocalDate paymentDate;
    private boolean isOneDayJob;

    public static JobPostingSearchDTO of(Long jobPostingId, String title, String location,
                                         LocalDateTime workDate, BigDecimal dailyWage, LocalDate paymentDate,
                                         boolean isOneDayJob){
        return JobPostingSearchDTO.builder()
                .jobPostingId(jobPostingId)
                .title(title)
                .location(location)
                .workDate(workDate)
                .dailyWage(dailyWage)
                .paymentDate(paymentDate)
                .isOneDayJob(isOneDayJob)
                .build();
    }

    public static JobPostingSearchDTO from(JobPosting jobPosting){
        return JobPostingSearchDTO.builder()
                .jobPostingId(jobPosting.getJobPostingId())
                .title(jobPosting.getTitle())
                .location(jobPosting.getLocation())
                .workDate(jobPosting.getWorkDate())
                .dailyWage(jobPosting.getDailyWage())
                .paymentDate(jobPosting.getPaymentDate())
                .isOneDayJob(jobPosting.isOneDayJob())
                .build();
    }
}

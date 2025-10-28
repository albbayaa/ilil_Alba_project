package com.ilil.alba.adapter.in.web.jobPosting.request;

import com.ilil.alba.domain.base.IsOneDayJob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@AllArgsConstructor
@Builder
public class JobPostingRequest {

    private Long jobPostingId;

    private String title;

    private String detail;

    private String location;

    private LocalDate workDate;

    private BigDecimal dailyWage;

    private LocalDate paymentDate;

    private IsOneDayJob isOneDayJob;

    private Long memberId;


    public static JobPostingRequest of(String title, String detail,String location,
                                       LocalDate workDate, BigDecimal dailyWage,
                                       LocalDate paymentDate, IsOneDayJob isOneDayJob, Long memberId) {
        return JobPostingRequest.builder()
                .title(title)
                .detail(detail)
                .location(location)
                .workDate(workDate)
                .dailyWage(dailyWage)
                .paymentDate(paymentDate)
                .isOneDayJob(isOneDayJob)
                .memberId(memberId)
                .build();
    }
}

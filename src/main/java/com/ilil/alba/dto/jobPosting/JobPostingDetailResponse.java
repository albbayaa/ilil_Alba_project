package com.ilil.alba.dto.jobPosting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class JobPostingDetailResponse {
    private Long jobPostingId;

    private String title;

    private String detail;

    private String location;

    private LocalDate workDate;

    private BigDecimal dailyWage;

    private LocalDate paymentDate;

    private boolean isOneDayJob;

    private String nickname;


    public static JobPostingDetailResponse of(String title, String detail,String location,
                                       LocalDate workDate, BigDecimal dailyWage,
                                       LocalDate paymentDate, boolean isOneDayJob,  String nickname) {
        return JobPostingDetailResponse.builder()
                .title(title)
                .detail(detail)
                .location(location)
                .workDate(workDate)
                .dailyWage(dailyWage)
                .paymentDate(paymentDate)
                .isOneDayJob(Boolean.TRUE.equals(isOneDayJob))
                .nickname(nickname)
                .build();
    }
}

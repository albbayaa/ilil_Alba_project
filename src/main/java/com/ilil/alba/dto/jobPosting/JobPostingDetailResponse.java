package com.ilil.alba.dto.jobPosting;

import com.ilil.alba.domain.JobPosting;
import com.ilil.alba.domain.base.IsCertification;
import com.ilil.alba.domain.base.IsOneDayJob;
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

    private IsOneDayJob isOneDayJob;

    private String nickname;

    private IsCertification isCertification;


    public static JobPostingDetailResponse of(Long jobPostingId, String title, String detail, String location,
                                              LocalDate workDate, BigDecimal dailyWage,
                                              LocalDate paymentDate, IsOneDayJob isOneDayJob, String nickname, IsCertification isCertification) {
        return JobPostingDetailResponse.builder()
                .title(title)
                .detail(detail)
                .location(location)
                .workDate(workDate)
                .dailyWage(dailyWage)
                .paymentDate(paymentDate)
                .isOneDayJob(isOneDayJob)
                .nickname(nickname)
                .isCertification(isCertification)
                .build();
    }

    public static JobPostingDetailResponse from(JobPosting jobPosting){
        return JobPostingDetailResponse.builder()
                .title(jobPosting.getTitle())
                .detail(jobPosting.getDetail())
                .location(jobPosting.getLocation())
                .workDate(jobPosting.getWorkDate())
                .dailyWage(jobPosting.getDailyWage())
                .paymentDate(jobPosting.getPaymentDate())
                .isOneDayJob(jobPosting.getIsOneDayJob())
                .nickname(jobPosting.getMember().getNickname())
                .isCertification(jobPosting.getMember().getIsCertification())
                .build();
    }
}

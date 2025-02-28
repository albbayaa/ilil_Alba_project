package com.ilil.alba.dto.jobPosting;

import com.ilil.alba.domain.base.IsOneDayJob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class JobPostingSearchRequest {

    private String title;
    private String location;

    private LocalDate workDate;
    private IsOneDayJob isOneDayJob;

    public static JobPostingSearchRequest of(String title, String location,
                                             LocalDate workDate, IsOneDayJob isOneDayJob) {
        return JobPostingSearchRequest.builder()
                .title(title)
                .location(location)
                .workDate(workDate)
                .isOneDayJob(isOneDayJob)
                .build();
    }

}

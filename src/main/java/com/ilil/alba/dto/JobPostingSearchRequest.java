package com.ilil.alba.dto;

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
    private boolean isOneDayJob;

    public static JobPostingSearchRequest of(String title, String location,
                                             LocalDate workDate, Boolean isOneDayJob) {
        return JobPostingSearchRequest.builder()
                .title(title)
                .location(location)
                .workDate(workDate)
                .isOneDayJob(Boolean.TRUE.equals(isOneDayJob))
                .build();
    }

}

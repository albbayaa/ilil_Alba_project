package com.ilil.alba.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPosting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Job_posting_id")
    private Long JobPostingId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDateTime workDate;

    @Column(nullable = false)
    private BigDecimal dailyWage;

    @Column(nullable = false)
    private LocalDate paymentDate;

    @Column(nullable = false)
    private boolean isOneDayJob;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

}

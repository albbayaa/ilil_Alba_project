package com.ilil.alba.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Job_application_id")
    private Long JobApplicationId;

    @Column(nullable = false)
    private String applicantName;

    @Column(nullable = false)
    private String contactInfo;

    @Column(nullable = false)
    private String resumeText;

    @Column(nullable = false)
    private LocalDate applicationDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}

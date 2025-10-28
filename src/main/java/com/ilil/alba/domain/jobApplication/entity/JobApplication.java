package com.ilil.alba.domain.jobApplication.entity;

import com.ilil.alba.domain.jobPosting.entity.JobPosting;
import com.ilil.alba.domain.base.BaseStatus;
import com.ilil.alba.domain.base.BaseTime;
import com.ilil.alba.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobApplication extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_application_id")
    private Long jobApplicationId;

    @Column(nullable = false)
    private String applicantName;

    @Column(nullable = false)
    private String contactInfo;

    @Column(nullable = false)
    private String resumeText;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(10)")
    private BaseStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "job_posting_id", nullable = false)
    private JobPosting jobPosting;
}

package com.ilil.alba.adapter.config;

import com.ilil.alba.application.jobApplication.port.in.JobApplicationPort;
import com.ilil.alba.application.jobApplication.port.out.JobApplicationRepositoryPort;
import com.ilil.alba.application.jobPosting.port.in.JobPostingPort;
import com.ilil.alba.application.jobPosting.port.out.JobPostingRepositoryPort;
import com.ilil.alba.application.member.port.out.MemberRepositoryPort;
import com.ilil.alba.application.member.usecase.MemberApplicationUseCase;
import com.ilil.alba.application.jobPosting.usecase.JobPostingApplicationUseCase;
import com.ilil.alba.application.jobApplication.usecase.JobApplicationApplicationUseCase;
import com.ilil.alba.application.member.port.in.MemberCommandPort;
import com.ilil.alba.application.member.port.in.MemberQueryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HexagonalArchitectureConfig {

    // ===== Member =====
    @Bean
    public MemberCommandPort memberCommandPort(MemberRepositoryPort memberRepositoryPort) {
        return new MemberApplicationUseCase(memberRepositoryPort);
    }

    @Bean
    public MemberQueryPort memberQueryPort(MemberRepositoryPort memberRepositoryPort) {
        return new MemberApplicationUseCase(memberRepositoryPort);
    }

    // ===== JobPosting =====
    @Bean
    public JobPostingPort jobPostingPort(JobPostingRepositoryPort jobPostingRepositoryPort) {
        return new JobPostingApplicationUseCase(jobPostingRepositoryPort);
    }

    // ===== JobApplication =====
    @Bean
    public JobApplicationPort jobApplicationPort(JobApplicationRepositoryPort jobApplicationRepositoryPort) {
        return new JobApplicationApplicationUseCase(jobApplicationRepositoryPort);
    }
}

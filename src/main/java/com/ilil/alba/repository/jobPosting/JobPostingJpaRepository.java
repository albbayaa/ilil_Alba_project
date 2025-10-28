package com.ilil.alba.repository.jobPosting;

import com.ilil.alba.domain.jobPosting.entity.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobPostingJpaRepository extends JpaRepository<JobPosting, Long> {

    @Query("SELECT jp " +
            "FROM JobPosting jp " +
            "JOIN FETCH jp.member m " +
            "WHERE jp.jobPostingId = :jobPostingId")
    Optional<JobPosting> jobPostingDetail(Long jobPostingId);

}

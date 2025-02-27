package com.ilil.alba.repository.jobPosting;

import com.ilil.alba.domain.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostingJpaRepository extends JpaRepository<JobPosting, Long> {

}

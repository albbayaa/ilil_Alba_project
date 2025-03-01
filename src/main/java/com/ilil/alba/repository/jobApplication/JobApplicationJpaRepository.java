package com.ilil.alba.repository.jobApplication;

import com.ilil.alba.domain.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplicationJpaRepository extends JpaRepository<JobApplication,Long> {
}

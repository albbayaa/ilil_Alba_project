package com.ilil.alba.adapter.out.persistence.jobApplication;

import com.ilil.alba.domain.jobApplication.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplicationJpaRepository extends JpaRepository<JobApplication,Long> {
}

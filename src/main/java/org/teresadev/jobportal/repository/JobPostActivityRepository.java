package org.teresadev.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.teresadev.jobportal.entity.JobPostActivity;

@Repository
public interface JobPostActivityRepository extends JpaRepository<JobPostActivity, Integer> {
}

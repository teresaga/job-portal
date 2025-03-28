package org.teresadev.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.teresadev.jobportal.entity.JobPostActivity;
import org.teresadev.jobportal.entity.JobSeekerApply;
import org.teresadev.jobportal.entity.JobSeekerProfile;

import java.util.List;

@Repository
public interface JobSeekerApplyRepository extends JpaRepository<JobSeekerApply, Integer> {

    List<JobSeekerApply> findByUserId(JobSeekerProfile userId);
    List<JobSeekerApply> findByJob(JobPostActivity job);
}

package org.teresadev.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.teresadev.jobportal.entity.JobPostActivity;
import org.teresadev.jobportal.entity.JobSeekerProfile;
import org.teresadev.jobportal.entity.JobSeekerSave;

import java.util.List;

@Repository
public interface JobSeekerSaveRepository extends JpaRepository<JobSeekerSave, Integer> {

    List<JobSeekerSave> findByUserId(JobSeekerProfile userAccountId);
    List<JobSeekerSave> findByJob(JobPostActivity jobPostActivity);
}

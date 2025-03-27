package org.teresadev.jobportal.services;

import org.springframework.stereotype.Service;
import org.teresadev.jobportal.entity.JobPostActivity;
import org.teresadev.jobportal.repository.JobPostActivityRepository;

@Service
public class JobPostActivityService {
    private final JobPostActivityRepository jobPostActivityRepository;

    public JobPostActivityService(JobPostActivityRepository jobPostActivityRepository) {
        this.jobPostActivityRepository = jobPostActivityRepository;
    }

    public JobPostActivity addNew(JobPostActivity jobPostActivity) {
        return jobPostActivityRepository.save(jobPostActivity);
    }
}

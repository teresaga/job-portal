package org.teresadev.jobportal.services;

import org.springframework.stereotype.Service;
import org.teresadev.jobportal.entity.*;
import org.teresadev.jobportal.repository.JobPostActivityRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobPostActivityService {
    private final JobPostActivityRepository jobPostActivityRepository;

    public JobPostActivityService(JobPostActivityRepository jobPostActivityRepository) {
        this.jobPostActivityRepository = jobPostActivityRepository;
    }

    public JobPostActivity addNew(JobPostActivity jobPostActivity) {
        return jobPostActivityRepository.save(jobPostActivity);
    }

    public List<RecruiterJobsDto> getRecruiterJobs(int recruiterId) {
        // Get the result from the customize query
        List<IRecruiterJobs> recruiterJobsDtos = jobPostActivityRepository.getRecruiterJobs(recruiterId);

        List<RecruiterJobsDto> recruiterJobsDtoList = new ArrayList<>();
        // Contruct DTO based on info from database
        for (IRecruiterJobs rec : recruiterJobsDtos) {
            JobLocation loc = new JobLocation(rec.getLocationId(), rec.getCity(), rec.getState(), rec.getCountry());
            JobCompany comp = new JobCompany(rec.getCompanyId(), rec.getName(), "");

            recruiterJobsDtoList.add(new RecruiterJobsDto(rec.getTotalCandidates(), rec.getJob_post_id(), rec.getJob_title(), loc, comp));
        }

        return recruiterJobsDtoList;
    }
}

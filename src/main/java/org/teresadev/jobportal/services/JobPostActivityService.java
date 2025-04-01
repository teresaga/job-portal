package org.teresadev.jobportal.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.teresadev.jobportal.entity.*;
import org.teresadev.jobportal.repository.JobPostActivityRepository;
import org.teresadev.jobportal.repository.JobSeekerApplyRepository;
import org.teresadev.jobportal.repository.JobSeekerSaveRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class JobPostActivityService {
    private final JobPostActivityRepository jobPostActivityRepository;
    private final JobSeekerSaveService jobSeekerSaveService;
    private final JobSeekerApplyService jobSeekerApplyService;
    private final JobSeekerSaveRepository jobSeekerSaveRepository;
    private final JobSeekerApplyRepository jobSeekerApplyRepository;

    public JobPostActivityService(JobPostActivityRepository jobPostActivityRepository, JobSeekerSaveService jobSeekerSaveService, JobSeekerApplyService jobSeekerApplyService, JobSeekerSaveRepository jobSeekerSaveRepository, JobSeekerApplyRepository jobSeekerApplyRepository) {
        this.jobPostActivityRepository = jobPostActivityRepository;
        this.jobSeekerSaveService = jobSeekerSaveService;
        this.jobSeekerApplyService = jobSeekerApplyService;
        this.jobSeekerSaveRepository = jobSeekerSaveRepository;
        this.jobSeekerApplyRepository = jobSeekerApplyRepository;
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

    public JobPostActivity getOne(int id) {
        return jobPostActivityRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
    }

    public List<JobPostActivity> getAll() {
        return jobPostActivityRepository.findAll();
    }

    public List<JobPostActivity> search(String job, String location, List<String> type, List<String> remote, LocalDate searchDate) {
        return Objects.isNull(searchDate)?jobPostActivityRepository.searchWithoutDate(job, location, remote, type) :
                jobPostActivityRepository.search(job, location, remote, type, searchDate);
    }

    @Transactional
    public void delete(int id) {
        JobPostActivity jobPostActivity = jobPostActivityRepository.findById(id).orElse(null);

        if (jobPostActivity != null) {
            jobSeekerSaveRepository.deleteByJob(jobPostActivity.getJobPostId());
            jobSeekerApplyRepository.deleteByJob(jobPostActivity.getJobPostId());
            jobPostActivityRepository.deleteById(id);
        }

    }
}

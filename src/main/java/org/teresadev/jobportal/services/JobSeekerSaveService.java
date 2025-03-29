package org.teresadev.jobportal.services;

import org.springframework.stereotype.Service;
import org.teresadev.jobportal.entity.JobPostActivity;
import org.teresadev.jobportal.entity.JobSeekerProfile;
import org.teresadev.jobportal.entity.JobSeekerSave;
import org.teresadev.jobportal.repository.JobSeekerSaveRepository;

import java.util.List;

@Service
public class JobSeekerSaveService {

    private final JobSeekerSaveRepository jobSeekerSaveRepository;

    public JobSeekerSaveService(JobSeekerSaveRepository jobSeekerSaveRepository) {
        this.jobSeekerSaveRepository = jobSeekerSaveRepository;
    }

    public List<JobSeekerSave> getCandidateJob(JobSeekerProfile userAccountId) {
        return jobSeekerSaveRepository.findByUserId(userAccountId);
    }

    public List<JobSeekerSave> getJobCandidates(JobPostActivity job) {
        return jobSeekerSaveRepository.findByJob(job);
    }

    public void addNew(JobSeekerSave jobSeekerSave) {
        jobSeekerSaveRepository.save(jobSeekerSave);
    }
}

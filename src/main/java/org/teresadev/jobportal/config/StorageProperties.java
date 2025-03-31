package org.teresadev.jobportal.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {
    private String jobseeker;
    private String recruiter;

    public String getJobseeker() {
        return jobseeker;
    }

    public void setJobseeker(String jobseeker) {
        this.jobseeker = jobseeker;
    }

    public String getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(String recruiter) {
        this.recruiter = recruiter;
    }
}

package org.teresadev.jobportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.teresadev.jobportal.entity.JobPostActivity;
import org.teresadev.jobportal.services.JobPostActivityService;
import org.teresadev.jobportal.services.UsersService;

@Controller
public class JobSeekerApplyController {

    private final JobPostActivityService jobPostActivityService;
    private final UsersService usersService;

    public JobSeekerApplyController(JobPostActivityService jobPostActivityService, UsersService usersService) {
        this.jobPostActivityService = jobPostActivityService;
        this.usersService = usersService;
    }

    @GetMapping("/job-details-apply/{id}")
    public String display(@PathVariable("id") int id, Model model) {
        JobPostActivity jobDetails = jobPostActivityService.getOne(id);

        model.addAttribute("jobDetails", jobDetails);
        model.addAttribute("user", usersService.getCurrentUserProfile());

        return "job-details";
    }

}

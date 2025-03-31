package org.teresadev.jobportal.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.teresadev.jobportal.config.StorageProperties;
import org.teresadev.jobportal.entity.RecruiterProfile;
import org.teresadev.jobportal.entity.Users;
import org.teresadev.jobportal.repository.UsersRepository;
import org.teresadev.jobportal.services.RecruiterProfileService;
import org.teresadev.jobportal.util.FileUploadUtil;

import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/recruiter-profile")
public class RecruiterProfileController {

    private final UsersRepository usersRepository;
    private final RecruiterProfileService recruiterProfileService;

    private final StorageProperties storageProperties;

    public RecruiterProfileController(UsersRepository usersRepository,
                                      RecruiterProfileService recruiterProfileService,
                                      StorageProperties storageProperties) {
        this.usersRepository = usersRepository;
        this.recruiterProfileService = recruiterProfileService;
        this.storageProperties = storageProperties;
    }

    @GetMapping("/")
    public String recruiterProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();

            Users users = usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("Could not found user"));

            Optional<RecruiterProfile> recruiterProfile = recruiterProfileService.getOne(users.getUserId());

            if (!recruiterProfile.isEmpty()) {
                model.addAttribute("profile", recruiterProfile.get());
            }
        }

        return "recruiter-profile";
    }

    @PostMapping("/addNew")
    public String addNew(RecruiterProfile recruiterProfile, @RequestParam("image") MultipartFile image, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof  AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            Users users = usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("Could not found user"));

            recruiterProfile.setUserId(users);
            recruiterProfile.setUserAccountId(users.getUserId());
        }

        model.addAttribute("profile", recruiterProfile);

        // process image
        String fileName ="";
        if (!image.getOriginalFilename().equals("")) {
            fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
            recruiterProfile.setProfilePhoto(fileName);
        }

        RecruiterProfile savedUser = recruiterProfileService.addNew(recruiterProfile);

        String uploadDir = storageProperties.getRecruiter() + savedUser.getUserAccountId();

        try {
            // read profile image from request - multipartfile
            // then save image on the server directory: photos/recruiter
            FileUploadUtil.saveFile(uploadDir, fileName, image);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "redirect:/dashboard/";
    }
}

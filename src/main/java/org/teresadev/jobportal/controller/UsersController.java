package org.teresadev.jobportal.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.teresadev.jobportal.entity.Users;
import org.teresadev.jobportal.entity.UsersType;
import org.teresadev.jobportal.services.UsersService;
import org.teresadev.jobportal.services.UsersTypeService;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    private final UsersTypeService usersTypeService;
    private final UsersService usersService;

    public UsersController(UsersTypeService usersTypeService, UsersService usersService) {
        this.usersTypeService = usersTypeService;
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        List<UsersType> usersTypes = usersTypeService.getAll();
        model.addAttribute("getAllTypes", usersTypes);
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register/new")
    public String userRegistration(@Valid Users user, Model model) {
        //System.out.println("User :: " + user);

        Optional<Users> optionalUser = usersService.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            model.addAttribute("error", "Email already registered, please choose another one");
            List<UsersType> usersTypes = usersTypeService.getAll();
            model.addAttribute("getAllTypes", usersTypes);
            model.addAttribute("user", new Users());
            return "register";
        }
        usersService.addNew(user);
        return "dashboard";
    }
}

package org.teresadev.jobportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.teresadev.jobportal.entity.Users;
import org.teresadev.jobportal.entity.UsersType;
import org.teresadev.jobportal.services.UsersTypeService;

import java.util.List;

@Controller
public class UsersController {

    private final UsersTypeService usersTypeService;

    public UsersController(final UsersTypeService usersTypeService) {
        this.usersTypeService = usersTypeService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        List<UsersType> usersTypes = usersTypeService.getAll();
        model.addAttribute("getAllTypes", usersTypes);
        model.addAttribute("user", new Users());
        return "register";
    }
}

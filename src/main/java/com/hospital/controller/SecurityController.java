package com.hospital.controller;

import com.hospital.model.Activity;
import com.hospital.model.HospitalUser;
import com.hospital.repository.ActivityRepository;
import com.hospital.service.HospitalUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Nikita Krutoguz
 */
@Controller
public class SecurityController {

    private final HospitalUserService hospitalUserService;
    private final ActivityRepository activityRepository;

    public SecurityController(HospitalUserService hospitalUserService, ActivityRepository activityRepository) {
        this.hospitalUserService = hospitalUserService;
        this.activityRepository = activityRepository;
    }

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "/system/login";
    }

    @GetMapping(value = "/403")
    public String error() {
        return "/system/403";
    }

    @GetMapping(value = "/profile")
    public String profile(Model model) {
        final HospitalUser currentUser = hospitalUserService.findCurrentUser();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("patients", currentUser.getPatients());
        return "/system/profile";
    }

    @GetMapping(value = "/activity")
    public String activity(Model model) {
        final List<Activity> activities = activityRepository.findAll();
        model.addAttribute("activities", activities);
        return "/system/activity";
    }

    @ResponseBody
    @GetMapping(value = "/is-duplicate-user-login/{login}", produces = "application/json")
    public final boolean isDuplicateUser(@PathVariable("login") String login) {
        return hospitalUserService.isDuplicateUser(login);
    }
}

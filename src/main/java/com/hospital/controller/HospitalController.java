package com.hospital.controller;

import com.hospital.model.HospitalUser;
import com.hospital.repository.UserStatusRepository;
import com.hospital.service.HospitalUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Nikita Krutoguz
 */
@Controller
public class HospitalController {
    private final HospitalUserService hospitalUserService;
    private final UserStatusRepository userStatusRepository;

    public HospitalController(HospitalUserService hospitalUserService, UserStatusRepository userStatusRepository) {
        this.hospitalUserService = hospitalUserService;
        this.userStatusRepository = userStatusRepository;
    }

    @GetMapping(value = "/doctor/list")
    public String listPatients(final Model model) {
        model.addAttribute("doctors", hospitalUserService.getDoctors());
        return "doctor/list";
    }

    @GetMapping(path = "/doctor/info/{id}")
    public String infoPatient(final Model model, @PathVariable("id") final Long id) {
        if (hospitalUserService.findOne(id) == hospitalUserService.findCurrentUser()) {
            return "redirect:/profile";
        }
        model.addAttribute("doctor", hospitalUserService.findOne(id));
        model.addAttribute("patients", hospitalUserService.patients(id));
        return "doctor/info";
    }

    @GetMapping(path = "/doctor/edit/{id}")
    public String editPatient(final Model model, @PathVariable("id") final Long id) {
        final HospitalUser doctor = hospitalUserService.findOne(id);
        model.addAttribute("doctor", doctor);
        model.addAttribute("patients", doctor.getPatients());
        model.addAttribute("statuses", userStatusRepository.findAll());
        return "doctor/edit";
    }

    @PostMapping(value = "/doctor/update")
    public final String doctorUpdate(final @RequestParam Long id, final @RequestParam String name, final @RequestParam String surname,
                                     final @RequestParam String login, final @RequestParam String position, final @RequestParam Long status) {
        hospitalUserService.hospitalUserUpdate(id, name, surname, login, position, status);
        return "redirect:/doctor/info/" + id;
    }

    @GetMapping(path = "doctor/add")
    public String createDoctor(final Model model) {
        model.addAttribute("doctor", new HospitalUser());
        model.addAttribute("statuses", userStatusRepository.findAll());
        return "doctor/add";
    }

    @PostMapping(value = "/doctor/save")
    public final String saveDoctor(final @RequestParam("name") String name,
                                   final @RequestParam("surname") String surname,
                                   final @RequestParam("login") String login,
                                   final @RequestParam("password") String password,
                                   final @RequestParam("position") String position,
                                   final @RequestParam("dateBirth") String dateBirth,
                                   final @RequestParam("status") Long status) {
        hospitalUserService.createDoctor(name, surname, login, password, position, dateBirth, status);
        return "redirect:/doctor/list";
    }

    @ResponseBody
    @GetMapping(value = "/is-user-free/{id}", produces = "application/json")
    public final boolean isDuplicateUser(@PathVariable("id") final Long id) {
        return hospitalUserService.isFree(id);
    }
}

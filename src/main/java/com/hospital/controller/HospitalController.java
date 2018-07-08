package com.hospital.controller;

import com.hospital.model.HospitalUser;
import com.hospital.repository.HospitalUserRepository;
import com.hospital.service.HospitalUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Nikita Krutoguz
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HospitalController {
    private final HospitalUserService hospitalUserService;

    @RequestMapping(value = "/doctor/list", method = RequestMethod.GET)
    public String listPatients(final Model model) {
        model.addAttribute("doctors", hospitalUserService.getDoctors());
        return "doctor/list";
    }

    @RequestMapping(path = "/doctor/info/{id}", method = RequestMethod.GET)
    public String infoPatient(final Model model, @PathVariable("id") final Long id) {
        model.addAttribute("doctor", hospitalUserService.findOne(id));
        model.addAttribute("patients", hospitalUserService.patients(id));
        return "doctor/info";
    }

    @RequestMapping(path = "/doctor/edit/{id}", method = RequestMethod.GET)
    public String editPatient(final Model model, @PathVariable("id") final Long id) {
        final HospitalUser doctor = hospitalUserService.findOne(id);
        model.addAttribute("doctor", doctor);
        model.addAttribute("patients", doctor.getPatients());
        return "doctor/edit";
    }

    @RequestMapping(value = "/doctor/update", method = RequestMethod.POST)
    public final String doctorUpdate(final @RequestParam Long id, final @RequestParam String name, final @RequestParam String surname, final @RequestParam String position) {
        hospitalUserService.hospitalUserUpdate(id, name, surname, position);
        return "redirect:/doctor/info/" + id;
    }

    @RequestMapping(path = "doctor/add", method = RequestMethod.GET)
    public String createDoctor(final Model model) {
        model.addAttribute("doctor", new HospitalUser());
        return "doctor/add";
    }

    @RequestMapping(value = "/doctor/save", method = RequestMethod.POST)
    public final String saveDoctor(final @RequestParam("name") String name,
                                    final @RequestParam("surname") String surname,
                                    final @RequestParam("login") String login,
                                    final @RequestParam("password") String password,
                                    final @RequestParam("position") String position,
                                   final @RequestParam("dateBirth") String dateBirth) {
        hospitalUserService.createDoctor(name, surname, login, password, position,dateBirth);
        return "redirect:/doctor/list";
    }

}

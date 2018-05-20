package com.hospital.controller;

import com.hospital.repository.HospitalUserRepository;
import com.hospital.service.HospitalUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Nikita Krutoguz
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HospitalController {
    private final HospitalUserRepository hospitalUserRepository;
    private final HospitalUserService hospitalUserService;

    @RequestMapping(value = "/doctor/list", method = RequestMethod.GET)
    public String listPatients(final Model model) {
        model.addAttribute("doctors", hospitalUserService.getDoctors());
        return "doctor/list";
    }

    @RequestMapping(path = "/doctor/info/{id}", method = RequestMethod.GET)
    public String infoPatient(final Model model, @PathVariable("id") final Long id) {
        model.addAttribute("doctor", hospitalUserRepository.findOne(id));
        model.addAttribute("patients", hospitalUserService.patients(id));
        return "doctor/info";
    }
/*
    @RequestMapping(path = "/patient/edit/{id}", method = RequestMethod.GET)
    public String editPatient(final Model model, @PathVariable("id") final Long id) {
        model.addAttribute("patient", patientRepository.findOne(id));
        model.addAttribute("doctors", hospitalUserService.getDoctors());
        return "patient/edit";
    }



   @RequestMapping(path = "patient/add", method = RequestMethod.GET)
    public String createPatient(final Model model) {
        model.addAttribute("patient", new Patient());
        model.addAttribute("doctors", hospitalUserService.getDoctors());
        return "patient/add";
    }

   @RequestMapping(value = "/patient/save", method = RequestMethod.POST)
    public final String savePatient(final @RequestParam("name") String name,
                                    final @RequestParam("surname") String surname,
                                    final @RequestParam("doctorId") Long doctorId) {
        patientService.createPatient(name, surname, doctorId);
        return "redirect:/patient/list";
    }

    @RequestMapping(value = "/patient/update", method = RequestMethod.POST)
    public final String patientUpdate(final @RequestParam Long id, final @RequestParam String name, final @RequestParam String surname, final @RequestParam Long doctorId) {
        patientService.patientUpdate(id, name, surname, doctorId);
        return "redirect:/patient/info/" + id;
    }*/
}

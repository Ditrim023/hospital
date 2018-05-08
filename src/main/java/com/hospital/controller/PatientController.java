package com.hospital.controller;

import com.hospital.model.Patient;
import com.hospital.repository.PatientRepository;
import com.hospital.service.HospitalUserService;
import com.hospital.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nikita Krutoguz
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientController {
    private final PatientRepository patientRepository;
    private final PatientService patientService;
    private final HospitalUserService hospitalUserService;


    @RequestMapping(value = "/patient/list", method = RequestMethod.GET)
    public String root(final Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "patient/list";
    }

    @RequestMapping(path = "patient/add", method = RequestMethod.GET)
    public String createPatient(final Model model) {
        model.addAttribute("patient", new Patient());
        model.addAttribute("doctors",hospitalUserService.getDoctors());
        return "patient/add";
    }

    @RequestMapping(value = "/patient/save", method = RequestMethod.POST)
    public final String savePatient(final @RequestParam("name") String name,
                                    final @RequestParam("surname") String surname,
                                    final @RequestParam("doctorId") Long doctorId) {
        patientService.create(name,surname,doctorId);
        return "redirect:/patient/list";
    }

    @RequestMapping(path = "/patient/edit/{id}", method = RequestMethod.GET)
    public String editPatient(final Model model, @PathVariable("id") final Long id) {
        model.addAttribute("patient", patientRepository.findOne(id));
        return "patient/edit";
    }

    @RequestMapping(value = "/patient/update", method = RequestMethod.POST)
    public final String update(final @RequestParam Long id, final @RequestParam String name, final @RequestParam String surname) {
        patientService.update(id,name,surname);
        return "redirect:/patient/list";
    }
}

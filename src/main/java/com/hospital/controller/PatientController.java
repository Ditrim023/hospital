package com.hospital.controller;

import com.hospital.model.Patient;
import com.hospital.repository.PatientRepository;
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
public class PatientController {
    private final PatientRepository patientRepository;


    @RequestMapping(value = "/patient/list", method = RequestMethod.GET)
    public String root(final Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "patient/list";
    }

    @RequestMapping(path = "patient/add", method = RequestMethod.GET)
    public String createPatient(final Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/add";
    }

    @RequestMapping(value = "/patient/save", method = RequestMethod.POST)
    public String savePatient(final Patient pat) {
        patientRepository.save(pat);
        return "redirect:/patient/list";
    }

    @RequestMapping(path = "/patient/edit/{id}", method = RequestMethod.GET)
    public String editPatient(final Model model, @PathVariable("id") final Long id) {
        model.addAttribute("patient", patientRepository.findOne(id));
        return "patient/edit";
    }
}

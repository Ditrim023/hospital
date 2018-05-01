package com.hospital.controller;

import com.hospital.model.Patient;
import com.hospital.repository.PatientRepository;
import com.hospital.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final PatientService patientService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("403")
    public String error403() {
        return "403";
    }

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

    /*@RequestMapping(value = "/patient/updates", method = RequestMethod.POST)
    public final String update(final @RequestParam Long id, final @RequestParam String name,final @RequestParam String surname,final @RequestParam Integer age) {
        patientService.update(id,name,surname,age);
        return "redirect:/patient/list";
    }*/
}

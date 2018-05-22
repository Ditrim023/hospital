package com.hospital.controller;

import com.hospital.model.Comment;
import com.hospital.model.Patient;
import com.hospital.repository.PatientRepository;
import com.hospital.service.HospitalUserService;
import com.hospital.service.PatientService;
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
public class PatientController {
    private final PatientService patientService;
    private final HospitalUserService hospitalUserService;

    @RequestMapping(value = "/patient/list", method = RequestMethod.GET)
    public String listPatients(final Model model) {
        model.addAttribute("patients", patientService.findAll());
        return "patient/list";
    }

    @RequestMapping(path = "patient/add", method = RequestMethod.GET)
    public String createPatient(final Model model) {
        model.addAttribute("patient", new Patient());
        model.addAttribute("doctors", hospitalUserService.getDoctors());
        return "patient/add";
    }


    @RequestMapping(path = "/patient/info/{id}", method = RequestMethod.GET)
    public String infoPatient(final Model model, @PathVariable("id") final Long id) {
        model.addAttribute("patient", patientService.findOne(id));
        model.addAttribute("comment", new Comment());
        model.addAttribute("comments", patientService.getReverseList(id));
        return "patient/info";
    }

    @RequestMapping(path = "/patient/edit/{id}", method = RequestMethod.GET)
    public String editPatient(final Model model, @PathVariable("id") final Long id) {
        model.addAttribute("patient", patientService.findOne(id));
        model.addAttribute("doctors", hospitalUserService.getDoctors());
        return "patient/edit";
    }

    @RequestMapping(value = "/patient/save", method = RequestMethod.POST)
    public final String savePatient(final @RequestParam("name") String name,
                                    final @RequestParam("surname") String surname,
                                    final @RequestParam("dateBirth") String dateBirth,
                                    final @RequestParam("doctorId") Long doctorId) {
        patientService.createPatient(name, surname, dateBirth ,doctorId);
        return "redirect:/patient/list";
    }

    @RequestMapping(value = "/patient/update", method = RequestMethod.POST)
    public final String patientUpdate(final @RequestParam Long id, final @RequestParam String name, final @RequestParam String surname, final @RequestParam Long doctorId) {
        patientService.patientUpdate(id, name, surname, doctorId);
        return "redirect:/patient/info/" + id;
    }
}

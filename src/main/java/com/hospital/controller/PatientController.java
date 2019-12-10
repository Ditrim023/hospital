package com.hospital.controller;

import com.hospital.model.Comment;
import com.hospital.model.Patient;
import com.hospital.service.CommentService;
import com.hospital.service.HospitalUserService;
import com.hospital.service.PatientService;
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
public class PatientController {
    private final PatientService patientService;
    private final HospitalUserService hospitalUserService;
    private final CommentService commentService;
    private static final String PATIENT = "patient";
    public PatientController(PatientService patientService, HospitalUserService hospitalUserService, CommentService commentService) {
        this.patientService = patientService;
        this.hospitalUserService = hospitalUserService;
        this.commentService = commentService;
    }

    @GetMapping(value = "/patient/list")
    public String listPatients(final Model model) {
        model.addAttribute("patients", patientService.findAll());
        return "patient/list";
    }

    @GetMapping(path = "patient/add")
    public String createPatient(final Model model) {
        model.addAttribute(PATIENT, new Patient());
        model.addAttribute("doctors", hospitalUserService.getDoctors());
        return "patient/add";
    }


    @GetMapping(path = "/patient/info/{id}")
    public String infoPatient(final Model model, @PathVariable("id") final Long id) {
        model.addAttribute(PATIENT, patientService.findOne(id));
        model.addAttribute("comment", new Comment());
        model.addAttribute("comments", commentService.getReverseList(id));
        return "patient/info";
    }

    @GetMapping(path = "/patient/edit/{id}")
    public String editPatient(final Model model, @PathVariable("id") final Long id) {
        model.addAttribute(PATIENT, patientService.findOne(id));
        model.addAttribute("doctors", hospitalUserService.getDoctors());
        return "patient/edit";
    }

    @PostMapping(value = "/patient/save")
    public final String savePatient(final @RequestParam("name") String name,
                                    final @RequestParam("surname") String surname,
                                    final @RequestParam("dateBirth") String dateBirth,
                                    final @RequestParam("doctorId") Long doctorId) {
        patientService.createPatient(name, surname, dateBirth, doctorId);
        return "redirect:/patient/list";
    }

    @PostMapping(value = "/patient/update")
    public final String patientUpdate(final @RequestParam Long id, final @RequestParam String name, final @RequestParam String surname, final @RequestParam Long doctorId) {
        patientService.patientUpdate(id, name, surname, doctorId);
        return "redirect:/patient/info/" + id;
    }

    @ResponseBody
    @GetMapping(value = "/is-free-doc/{id}", produces = "application/json")
    public final boolean isFreeDoc(@PathVariable("id") final Long id) {
        return hospitalUserService.isFreeDoc(id);
    }
}

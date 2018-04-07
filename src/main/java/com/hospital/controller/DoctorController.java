package com.hospital.controller;

import com.hospital.model.Doctor;
import com.hospital.repository.DoctorRepository;
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
public class DoctorController {
    private final DoctorRepository doctorRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "layout/header";
    }

    @RequestMapping(value = "/doctor/list")
    public String root(final Model model) {
        model.addAttribute("doctors", doctorRepository.findAll());
        return "doctor/list";
    }

    @RequestMapping(value = "/doctor/add", method = RequestMethod.GET)
    public String createDoctor(final Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctor/add";
    }

    @RequestMapping(value = "/doctor/save", method = RequestMethod.POST)
    public String saveDoctor(final Doctor doc) {
        doctorRepository.save(doc);
        return "redirect:/doctor/list";
    }


    @RequestMapping(path = "/doctor/edit/{id}", method = RequestMethod.GET)
    public String dataDoctor(final Model model, @PathVariable("id") final Long id) {
        model.addAttribute("doctor", doctorRepository.findOne(id));
        return "doctor/edit";
    }

}



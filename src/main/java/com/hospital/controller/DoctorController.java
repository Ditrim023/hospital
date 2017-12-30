package com.hospital.controller;

import com.hospital.model.Doctor;
import com.hospital.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Nikita Krutoguz
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DoctorController {
    private final DoctorRepository doctorRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String root(Model model) {
        model.addAttribute("doctors",doctorRepository.findAll());
        return "list";
    }

    @RequestMapping(path = "/doctors/add", method = RequestMethod.GET)
    public String createDoctor(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "edit";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String saveProduct(Doctor doc) {
        doctorRepository.save(doc);
        return "redirect:/list";
    }



   /* @RequestMapping("/doctors/edit/{id}")
    public String editProduct(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("doctor", doctorRepository.findOne(Long.valueOf(id)));
        return "edit";
    }*/


}



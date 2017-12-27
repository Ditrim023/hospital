package com.hospital.controller;

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


    @RequestMapping(value="/", method = RequestMethod.GET)
    public String index(){
        return "index";
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listRabotniki(Model model) {
        model.addAttribute("doctors", doctorRepository.findAll());
        return "/list";
    }


}



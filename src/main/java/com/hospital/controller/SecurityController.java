package com.hospital.controller;

import com.hospital.model.HospitalUser;
import com.hospital.repository.HospitalUserRepository;
import com.hospital.utils.Util;
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
public class SecurityController {

    private final HospitalUserRepository hospitalUserRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String error() {
        return "/403";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model) {
        final HospitalUser currentUser = hospitalUserRepository.findUserByLogin(Util.getAuthorizedUserName());
        model.addAttribute("currentUser",currentUser);
       // model.addAttribute("hospSurname",currentUser.getName());
        return "/profile";
    }
}

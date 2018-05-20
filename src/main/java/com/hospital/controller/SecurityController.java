package com.hospital.controller;

import com.hospital.model.HospitalUser;
import com.hospital.repository.HospitalUserRepository;
import com.hospital.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        return "/system/login";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String error() {
        return "/system/403";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model) {
        final HospitalUser currentUser = hospitalUserRepository.findUserByLogin(Util.getAuthorizedUserName());
        model.addAttribute("currentUser",currentUser);
        model.addAttribute("patients",currentUser.getPatients());
        return "/system/profile";
    }
}

package com.hospital.controller;

import com.hospital.repository.CommentRepository;
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
public class CommentController {
    private final PatientService patientService;
    private final CommentRepository commentRepository;

    @RequestMapping(path = "/patient/comment/{id}", method = RequestMethod.GET)
    public String editComment(final Model model, @PathVariable("id") final Long id) {
        model.addAttribute("comment", commentRepository.findOne(id));
        return "patient/info";
    }

    @RequestMapping(value = "/comment/update", method = RequestMethod.POST)
    public final String commentUpdate(final @RequestParam Long id, final @RequestParam String text) {
        patientService.commentUpdate(id, text);
        final Long patientId = commentRepository.findOne(id).getPatient().getId();
        return "redirect:/patient/info/" + patientId;
    }

    @RequestMapping(value = "/comment/save/", method = RequestMethod.POST)
    public final String saveComment(final @RequestParam Long id, final @RequestParam String text) {
        patientService.saveComment(id, text);
        return "redirect:/patient/info/" + id;
    }
}

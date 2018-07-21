package com.hospital.controller;

import com.hospital.model.Comment;
import com.hospital.repository.CommentRepository;
import com.hospital.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nikita Krutoguz
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentController {
    private final PatientService patientService;
    private final CommentRepository commentRepository;

    @ResponseBody
    @RequestMapping(value = "/patient/comment/{id}", method = RequestMethod.GET)
    public String getComment(@PathVariable("id") final Long id) {
        String info = patientService.getOneComment(id);
        return info;
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

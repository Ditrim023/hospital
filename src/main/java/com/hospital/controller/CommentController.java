package com.hospital.controller;

import com.hospital.model.Comment;
import com.hospital.repository.CommentRepository;
import com.hospital.service.CommentService;
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
    private final CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/patient/comment/{id}", method = RequestMethod.GET)
    public String getComment(@PathVariable("id") final Long id) {
        return commentService.getTextComment(id);
    }

    @RequestMapping(value = "/comment/update", method = RequestMethod.POST)
    public final String commentUpdate(final @RequestParam Long id, final @RequestParam String text) {
        commentService.commentUpdate(id, text);
        final Long patientId = commentService.findOne(id).getPatient().getId();
        return "redirect:/patient/info/" + patientId;
    }

    @RequestMapping(value = "/comment/save/", method = RequestMethod.POST)
    public final String saveComment(final @RequestParam Long id, final @RequestParam String text) {
        commentService.saveComment(id, text);
        return "redirect:/patient/info/" + id;
    }
}

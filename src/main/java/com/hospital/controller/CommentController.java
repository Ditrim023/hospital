package com.hospital.controller;

import com.hospital.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Nikita Krutoguz
 */
@Controller
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ResponseBody
    @GetMapping(value = "/patient/comment/{id}")
    public String getComment(@PathVariable("id") final Long id) {
        return commentService.getTextComment(id);
    }

    @PostMapping(value = "/comment/update")
    public final String commentUpdate(final @RequestParam Long id, final @RequestParam String text) {
        commentService.commentUpdate(id, text);
        final Long patientId = commentService.findOne(id).getPatient().getId();
        return "redirect:/patient/info/" + patientId;
    }

    @PostMapping(value = "/comment/save/")
    public final String saveComment(final @RequestParam Long id, final @RequestParam String text) {
        commentService.saveComment(id, text);
        return "redirect:/patient/info/" + id;
    }
}

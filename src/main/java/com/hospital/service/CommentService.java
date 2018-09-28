package com.hospital.service;

import com.hospital.model.Comment;
import com.hospital.model.HospitalUser;
import com.hospital.model.Patient;
import com.hospital.repository.CommentRepository;
import com.hospital.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Nikita Krutoguz
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentService {
    private final CommentRepository commentRepository;
    private final PatientRepository patientRepository;
    private final HospitalUserService hospitalUserService;

    public final void commentUpdate(final Long id, final String text) {
        final Comment fromBase = commentRepository.findOne(id);
        final Patient patient = patientRepository.findOne(fromBase.getPatient().getId());
        final HospitalUser currentUser = hospitalUserService.findCurrentUser();
        final String lastEditor = currentUser.getName() + " " + currentUser.getSurname() + " - " + currentUser.getPosition();
        fromBase.setText(text);
        fromBase.setPatient(patient);
        fromBase.setDateLastChange(System.currentTimeMillis());
        fromBase.setLastEditor(lastEditor);
        fromBase.setLastEditorId(currentUser.getId());
        commentRepository.save(fromBase);
    }

    public final void saveComment(final Long patientId, final String text) {
        final Patient patient = patientRepository.findOne(patientId);
        final HospitalUser currentUser = hospitalUserService.findCurrentUser();
        final String author = currentUser.getName() + " " + currentUser.getSurname() + " - " + currentUser.getPosition();
        String lastEditor = author;
        final Long authorId = currentUser.getId();
        commentRepository.save(new Comment(text, patient, author, lastEditor, authorId, authorId));
    }

    public final List<Comment> getReverseList(final Long id) {
        List<Comment> reversList = patientRepository.findOne(id).getComments();
        Collections.reverse(reversList);
        return reversList;
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public final String getTextComment(final Long id) {
        return commentRepository.getTextComment(id);
    }

    public final Comment findOne(final Long commentId) {
        return commentRepository.findOne(commentId);
    }

    public final Comment add(Comment comment) {
        return commentRepository.save(comment);
    }
}

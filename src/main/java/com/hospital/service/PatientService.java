package com.hospital.service;

import com.hospital.model.Comment;
import com.hospital.model.HospitalUser;
import com.hospital.model.Patient;
import com.hospital.repository.CommentRepository;
import com.hospital.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Nikita Krutoguz
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientService {
    private final PatientRepository patientRepository;
    private final HospitalUserService hospitalUserService;
    private final CommentRepository commentRepository;


    public final void patientUpdate(final Long id, final String name, final String surname, final Long doctorId) {
        final Patient fromBase = patientRepository.findOne(id);
        final HospitalUser doctor = hospitalUserService.findOne(doctorId);
        fromBase.setName(name);
        fromBase.setSurname(surname);
        fromBase.setDoctor(doctor);
        patientRepository.save(fromBase);
    }

    public final void createPatient(final String name, final String surname, final Long doctorId) {
        HospitalUser doctor = hospitalUserService.findOne(doctorId);
        patientRepository.save(new Patient(name, surname, doctor));
    }

    public final void saveComment(final Long patientId, final String text) {
        final Patient patient = patientRepository.findOne(patientId);
        final HospitalUser currentUser = hospitalUserService.findUserByLogin();
        String author = currentUser.getName() + " " + currentUser.getSurname() + " - " + currentUser.getPosition();
        Long authorId = currentUser.getId();
        commentRepository.save(new Comment(text, patient, author, authorId));
    }

    public final List<Comment> getReverseList(final Long id) {
        List<Comment> reversList = patientRepository.findOne(id).getComments();
        Collections.reverse(reversList);
        return reversList;
    }

    public final void commentUpdate(final Long id, final String text) {
        final Comment fromBase = commentRepository.findOne(id);
        final Patient patient = patientRepository.findOne(fromBase.getPatient().getId());
        fromBase.setText(text);
        fromBase.setPatient(patient);
        fromBase.setDateLastChange(System.currentTimeMillis());
        commentRepository.save(fromBase);
    }


    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public final Patient findOne(final Long patientId) {
        return patientRepository.findOne(patientId);
    }
}

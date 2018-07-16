package com.hospital.service;

import com.hospital.model.Comment;
import com.hospital.model.HospitalUser;
import com.hospital.model.Patient;
import com.hospital.repository.CommentRepository;
import com.hospital.repository.PatientRepository;
import com.hospital.utils.Util;
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
        final Patient patient = patientRepository.findOne(id);
        final HospitalUser doctor = hospitalUserService.findOne(doctorId);
        final HospitalUser currentUser = hospitalUserService.findUserByLogin();
        final String author = currentUser.getName() + " " + currentUser.getSurname() + " - " + currentUser.getPosition();
        String lastEditor = author;
        final Long authorId = currentUser.getId();
        patient.setName(name);
        patient.setSurname(surname);
        if (patient.getDoctor() != doctor) {
            commentRepository.save(new Comment("Sent from " + patient.getDoctor().getSurname() + " to " + doctor.getSurname(), patient, author,lastEditor , authorId, authorId));
        }
        patient.setDoctor(doctor);
        patientRepository.save(patient);
    }

    public final void createPatient(final String name, final String surname,final String dateBirth, final Long doctorId) {
        HospitalUser doctor = hospitalUserService.findOne(doctorId);
        patientRepository.save(new Patient(name, surname, Util.getRealDate(dateBirth), doctor));
    }

    public final void saveComment(final Long patientId, final String text) {
        final Patient patient = patientRepository.findOne(patientId);
        final HospitalUser currentUser = hospitalUserService.findUserByLogin();
        final String author = currentUser.getName() + " " + currentUser.getSurname() + " - " + currentUser.getPosition();
        String lastEditor = author;
        final Long authorId = currentUser.getId();
        commentRepository.save(new Comment(text, patient, author,lastEditor, authorId, authorId));
    }

    public final List<Comment> getReverseList(final Long id) {
        List<Comment> reversList = patientRepository.findOne(id).getComments();
        Collections.reverse(reversList);
        return reversList;
    }

    public final void commentUpdate(final Long id, final String text) {
        final Comment fromBase = commentRepository.findOne(id);
        final Patient patient = patientRepository.findOne(fromBase.getPatient().getId());
        final HospitalUser currentUser = hospitalUserService.findUserByLogin();
        final String lastEditor = currentUser.getName() + " " + currentUser.getSurname() + " - " + currentUser.getPosition();
        fromBase.setText(text);
        fromBase.setPatient(patient);
        fromBase.setDateLastChange(System.currentTimeMillis());
        fromBase.setLastEditor(lastEditor);
        fromBase.setLastEditorId(currentUser.getId());
        commentRepository.save(fromBase);
    }


    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public final Patient findOne(final Long patientId) {
        return patientRepository.findOne(patientId);
    }
}

package com.hospital.service;

import com.hospital.model.Comment;
import com.hospital.model.HospitalUser;
import com.hospital.model.Patient;
import com.hospital.repository.CommentRepository;
import com.hospital.repository.HospitalUserRepository;
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
    private final HospitalUserRepository hospitalUserRepository;
    private final CommentRepository commentRepository;


    public final void patientUpdate(final Long id, final String name, final String surname, final Long doctorId) {
        final Patient fromBase = patientRepository.findOne(id);
        final HospitalUser doctor = hospitalUserRepository.findOne(doctorId);
        fromBase.setName(name);
        fromBase.setSurname(surname);
        fromBase.setDoctor(doctor);
        patientRepository.save(fromBase);
    }

    public final void create(final String name, final String surname, final Long doctorId) {
        HospitalUser doctor = hospitalUserRepository.findOne(doctorId);
        patientRepository.save(new Patient(name, surname, doctor));
    }

    public final void saveComment(final Long patientId,final String text){
        final Patient patient = patientRepository.findOne(patientId);
        final Comment comment = new Comment(text);
        commentRepository.save(comment);
        patient.getComments().add(comment);
        patientRepository.save(patient);
    }
    public final List<Comment> getReverselist(final Long id){
        List<Comment> reversList = patientRepository.findOne(id).getComments();
        Collections.reverse(reversList);
        return reversList;
    }

   /* public final void commentUpdate(final Long id, final String text, final Long patientId) {
        final Comment fromBase = commentRepository.findOne(id);
        final Patient patient = patientRepository.findOne(patientId);
        fromBase.setText(text);
        fromBase.setPatients();
        patientRepository.save(fromBase);
    }*/
}

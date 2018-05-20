package com.hospital.service;

import com.hospital.model.HospitalUser;
import com.hospital.model.Patient;
import com.hospital.repository.HospitalUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.dom.DocType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita Krutoguz
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HospitalUserService {
    private final HospitalUserRepository hospitalUserRepository;

    public final List<HospitalUser> getDoctors() {
        final List<HospitalUser> doctors = hospitalUserRepository.findAllUserByRoleId(2L);
        return doctors;
    }

    public final List<Patient> patients(final Long id){
        final HospitalUser doctor = hospitalUserRepository.findOne(id);
        List<Patient> patients = doctor.getPatients();
        return patients;
    }

    public void hospitalUserUpdate(final Long id, final String name, final String surname, final String position){
        final HospitalUser doctorFromBase = hospitalUserRepository.findOne(id);
        doctorFromBase.setName(name);
        doctorFromBase.setSurname(surname);
        doctorFromBase.setPosition(position);
        hospitalUserRepository.save(doctorFromBase);
    }

    public List<HospitalUser> findAll() {
        return hospitalUserRepository.findAll();
    }

    public final HospitalUser findOne(final Long hospitalUserId) {
        return hospitalUserRepository.findOne(hospitalUserId);
    }
}

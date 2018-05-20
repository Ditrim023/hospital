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

    public final List<Patient> patients(Long id){
        final HospitalUser doctor = hospitalUserRepository.findOne(id);
        List<Patient> patients = doctor.getPatients();
        return patients;
    }
}

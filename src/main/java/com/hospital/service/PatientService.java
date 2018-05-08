package com.hospital.service;

import com.hospital.model.HospitalUser;
import com.hospital.model.Patient;
import com.hospital.repository.HospitalUserRepository;
import com.hospital.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Nikita Krutoguz
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientService {
    private final PatientRepository patientRepository;
    private final HospitalUserRepository hospitalUserRepository;

    public final void update(final Long id, final String name, final String surname,final Long doctorId) {
        final Patient fromBase = patientRepository.findOne(id);
        final HospitalUser doctor = hospitalUserRepository.findOne(doctorId);
        fromBase.setName(name);
        fromBase.setSurname(surname);
        fromBase.setDoctor(doctor);
        patientRepository.save(fromBase);
    }
    public final void create(final String name,final String surname,final Long doctorId){
       HospitalUser doctor = hospitalUserRepository.findOne(doctorId);
       patientRepository.save(new Patient(name,surname,doctor));
    }
}

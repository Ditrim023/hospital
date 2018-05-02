package com.hospital.service;

import com.hospital.model.HospitalUser;
import com.hospital.model.Patient;
import com.hospital.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nikita Krutoguz
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientService {
     private final PatientRepository patientRepository;
     public final void update(final Long id,final String name,final String surname){
          final Patient fromBase = patientRepository.findOne(id);
          final HospitalUser doctor = fromBase.getDoctor();
          fromBase.setName(name);
          fromBase.setSurname(surname);
          fromBase.setDoctor(doctor);
          patientRepository.save(fromBase);
     }
}

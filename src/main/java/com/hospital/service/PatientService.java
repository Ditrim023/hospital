package com.hospital.service;

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

    /* public final void update(final Long id,final String name,final String surname,final Integer age){
          final Patient fromBase = patientRepository.findOne(id);
          List<Doctor> doctors = fromBase.getDoctors();
          fromBase.setName(name);
          fromBase.setSurname(surname);
          fromBase.setAge(age);
          fromBase.setDoctors(doctors);
          patientRepository.save(fromBase);
     }*/
}

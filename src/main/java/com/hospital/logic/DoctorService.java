package com.hospital.logic;

import com.hospital.model.Doctor;
import com.hospital.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author Nikita Krutoguz
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public final void add(String name, String surname){
        Random random = new Random();
        int age = 36 + random.nextInt(41 - 36);
        doctorRepository.save(new Doctor(name.trim(),surname.trim(),age));
    }
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public final Doctor findOne(final Long aboutProgramId) {
        return doctorRepository.findOne(aboutProgramId);
    }

    public Doctor getDoctorByName(String name) {
        return doctorRepository.findByName(name);
    }
}

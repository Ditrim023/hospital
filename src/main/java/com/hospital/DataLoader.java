package com.hospital;

import com.hospital.model.Doctor;
import com.hospital.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita Krutoguz
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataLoader implements ApplicationRunner {
    private final DoctorRepository doctorRepository;

    private final List<Doctor> doctors = new ArrayList<>();


    @Override
    public void run(ApplicationArguments args) {
        insertDoctors();
    }

    public void insertDoctors() {
        doctors.add(new Doctor("Ivan", "LOR", 40));
        doctors.add(new Doctor("Petr", "Terapevt", 42));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctorRepository.save(doctors);
    }


}




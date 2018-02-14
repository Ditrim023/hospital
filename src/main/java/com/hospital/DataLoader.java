package com.hospital;

import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import com.hospital.repository.DoctorRepository;
import com.hospital.repository.PatientRepository;
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
    private final PatientRepository patientRepository;

    private final List<Doctor> doctors = new ArrayList<>();
    private final List<Patient> patients = new ArrayList<>();


    @Override
    public void run(ApplicationArguments args) {
        insertDoctors();
        insertPatients();
    }

    public void insertDoctors() {
        doctors.add(new Doctor("Ivan", "LOR", 40));
        doctors.add(new Doctor("Petr", "Terapevt", 42));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctorRepository.save(doctors);
    }

    public void insertPatients() {
        patients.add(new Patient("Katya", "Golova", 34));
        patients.add(new Patient("Andrey", "Jivot", 51));
        patients.add(new Patient("Vasya", "Noga", 59));
        patients.add(new Patient("Jeka", "Ryka", 27));
        patients.add(new Patient("Nikola", "Yho", 11));
        patients.add(new Patient("Aizek", "Noz", 45));
        patientRepository.save(patients);
    }
}




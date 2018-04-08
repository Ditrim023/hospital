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
        getValue();
    }

    public void insertDoctors() {
        doctors.add(new Doctor("Ivan", "LOR", 40));
        doctors.add(new Doctor("Petr", "Terapevt", 42));
        doctors.add(new Doctor("Nikolay", "Sergeon", 32));
        doctorRepository.save(doctors);
    }

    public void insertPatients() {
        List<Doctor> tempDocs = new ArrayList<>();
        tempDocs.add(doctorRepository.findOne(1L));
        tempDocs.add(doctorRepository.findOne(2L));
        patientRepository.save(new Patient("Katya", "Golova",34));
        Patient katya = patientRepository.findByName("Katya");
        katya.setDoctors(tempDocs);
        patientRepository.save(katya);
        System.out.println("1");
        /*patients.add(new Patient("Andrey", "Jivot",doctorRepository.findAll(), 51));
        patientRepository.save(new Patient("Andrey", "Jivot",doctorRepository.findAll(), 51));
        System.out.println("2");
        patients.add(new Patient("Vasya", "Noga", doctorRepository.findAll(),59));
        System.out.println("3");
        patients.add(new Patient("Jeka", "Ryka",doctorRepository.findAll(), 27));
        System.out.println("4");
        patients.add(new Patient("Nikola", "Yho",doctorRepository.findAll(), 11));
        System.out.println("5");
        patients.add(new Patient("Aizek", "Noz", doctorRepository.findAll(),45));
        System.out.println("Done");*/
        patientRepository.save(patients);
    }

    public void getValue(){

    }
}




package com.hospital;

import com.hospital.model.Comment;
import com.hospital.model.HospitalUser;
import com.hospital.model.Patient;
import com.hospital.model.UserRole;
import com.hospital.repository.CommentRepository;
import com.hospital.repository.HospitalUserRepository;
import com.hospital.repository.PatientRepository;
import com.hospital.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita Krutoguz
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataLoader implements ApplicationRunner {
    private final PatientRepository patientRepository;
    private final HospitalUserRepository hospitalUserRepository;
    private final UserRoleRepository userRoleRepository;
    private final CommentRepository commentRepository;

    private final List<Patient> patients = new ArrayList<>();
    private final List<HospitalUser> hospitalUsers = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();

    private final UserRole admin = new UserRole("ROLE_ADMIN");
    private final UserRole doctor = new UserRole("ROLE_DOCTOR");
    private final UserRole receptionist = new UserRole("ROLE_RECEPTIONIST");

    @Override
    public void run(ApplicationArguments args) {
        insertRoles();
        insertHospitalUsers();
        insertPatients();

    }

    private void insertRoles() {
        userRoleRepository.save(admin);
        userRoleRepository.save(doctor);
        userRoleRepository.save(receptionist);
    }

    private void insertHospitalUsers() {
        HospitalUser hospitalUser1 = new HospitalUser("Nikita", "Krutoguz", "user0", new BCryptPasswordEncoder(10).encode("123456"), "07.02.1990", "SystemAdmin");
        hospitalUser1.setRole(userRoleRepository.findOne(1L));
        hospitalUsers.add(hospitalUser1);
        HospitalUser hospitalUser2 = new HospitalUser("Jeka", "Belov", "user1", new BCryptPasswordEncoder(10).encode("123456"), "07.02.1990", "Receptionist");
        hospitalUser2.setRole(userRoleRepository.findOne(3L));
        hospitalUsers.add(hospitalUser2);
        HospitalUser hospitalUser3 = new HospitalUser("Yra", "Knish", "user2", new BCryptPasswordEncoder(10).encode("123456"), "24.05.1990", "LOR");
        hospitalUser3.setRole(userRoleRepository.findOne(2L));
        hospitalUsers.add(hospitalUser3);
        HospitalUser hospitalUser4 = new HospitalUser("Leha", "Evty", "user3", new BCryptPasswordEncoder(10).encode("123456"), "26.02.1989", "Therapist");
        hospitalUser4.setRole(userRoleRepository.findOne(2L));
        hospitalUsers.add(hospitalUser4);
        HospitalUser hospitalUser5 = new HospitalUser("Maks", "Kop", "user4", new BCryptPasswordEncoder(10).encode("123456"), "15.07.1990", "Surgeon");
        hospitalUser5.setRole(userRoleRepository.findOne(2L));
        hospitalUsers.add(hospitalUser5);
        hospitalUserRepository.save(hospitalUsers);
    }

    private void insertPatients() {
        comments.add(new Comment("Hello"));
        comments.add(new Comment("My"));
        comments.add(new Comment("Dear"));
        comments.add(new Comment("Patients"));
        Patient patient1 = new Patient("Ivan", "Ivanov", "01.01.2000",comments);
        patient1.setDoctor(hospitalUserRepository.findOne(5L));
        patients.add(patient1);
        Patient patient2 = new Patient("Petr", "Petrov", "01.01.2000");
        patient2.setDoctor(hospitalUserRepository.findOne(3L));
        patients.add(patient2);
        Patient patient3 = new Patient("Sidr", "Sidirov", "01.01.2000");
        patient3.setDoctor(hospitalUserRepository.findOne(3L));
        patients.add(patient3);
        Patient patient4 = new Patient("Sergei", "Sergeev", "01.01.2000");
        patient4.setDoctor(hospitalUserRepository.findOne(4L));
        patients.add(patient4);
        Patient patient5 = new Patient("Anton", "Antonenko", "01.01.2000");
        patient5.setDoctor(hospitalUserRepository.findOne(4L));
        patients.add(patient5);
        Patient patient6 = new Patient("Fedr", "Fedorov", "01.01.2000");
        patient6.setDoctor(hospitalUserRepository.findOne(4L));
        patients.add(patient6);
        patientRepository.save(patients);
    }

    private void insertComment() {
        comments.add(new Comment("Hello"));
        comments.add(new Comment("My"));
        comments.add(new Comment("Dear"));
        comments.add(new Comment("Patients"));
        commentRepository.save(comments);
    }

    private void getValue() {

    }
}




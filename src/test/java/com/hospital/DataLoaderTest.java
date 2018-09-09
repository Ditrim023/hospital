package com.hospital;

import com.hospital.model.*;
import com.hospital.repository.*;
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
public class DataLoaderTest implements ApplicationRunner {
    private final PatientRepository patientRepository;
    private final HospitalUserRepository hospitalUserRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserStatusRepository userStatusRepository;
    private final CommentRepository commentRepository;

    private UserRole admin = new UserRole("ROLE_ADMIN");
    private UserRole doctor = new UserRole("ROLE_DOCTOR");

    private List<Patient> patients = new ArrayList<>();
    private List<HospitalUser> hospitalUsers = new ArrayList<>();
    private UserStatus free = new UserStatus("free");
    private UserStatus vacation = new UserStatus("vacation");

    @Override
    public void run(ApplicationArguments applicationArguments){
        insertRoles();
        insertStatuses();
        insertHospitalUsers();
        insertPatient();
        insertComment();
    }

    public void insertRoles() {
        userRoleRepository.save(admin);
        userRoleRepository.save(doctor);
    }

    public void insertStatuses() {
        userStatusRepository.save(free);
        userStatusRepository.save(vacation);
    }

    public void insertPatient() {
        Patient patient2 = new Patient("Test", "Testov", "01-01-2002");
        patient2.setDoctor(hospitalUserRepository.findOne(2L));
        patients.add(patient2);
        Patient patient3 = new Patient("Dmitr", "Dmitrov", "01-01-2002");
        patient3.setDoctor(hospitalUserRepository.findOne(2L));
        patients.add(patient3);
        Patient patient4 = new Patient("Comm", "Ment", "01-01-2002");
        patient4.setDoctor(hospitalUserRepository.findOne(2L));
        patients.add(patient4);
        patientRepository.save(patients);
    }

    public void insertHospitalUsers() {
        HospitalUser hospitalUser1 = new HospitalUser("Nikita", "Krutoguz", "user0", new BCryptPasswordEncoder(10).encode("123456"), "07-02-1990", "Admin");
        hospitalUser1.setRole(userRoleRepository.findOne(1L));
        hospitalUser1.setStatus(userStatusRepository.findOne(1L));
        hospitalUsers.add(hospitalUser1);
        HospitalUser hospitalUser2 = new HospitalUser("Yra", "Knish", "user1", new BCryptPasswordEncoder(10).encode("123456"), "24-05-1990", "LOR");
        hospitalUser2.setRole(userRoleRepository.findOne(2L));
        hospitalUser2.setStatus(userStatusRepository.findOne(2L));
        hospitalUser2.setPatients(patients);
        hospitalUsers.add(hospitalUser2);
        HospitalUser hospitalUser3 = new HospitalUser("DocTest", "Test", "user2", new BCryptPasswordEncoder(10).encode("123456"), "24-05-1990", "LOR");
        hospitalUser3.setRole(userRoleRepository.findOne(2L));
        hospitalUser3.setStatus(userStatusRepository.findOne(2L));
        hospitalUser3.setPatients(patients);
        hospitalUsers.add(hospitalUser3);
        hospitalUserRepository.save(hospitalUsers);
    }

     public void insertComment(){
         Comment comment = new Comment("Text Comment");
         comment.setPatient(patientRepository.findOne(3L));
         comment.setAuthor(hospitalUserRepository.findOne(3L).getName());
         comment.setAuthorId(3L);
         comment.setLastEditor(hospitalUserRepository.findOne(3L).getName());
         commentRepository.save(comment);
     }
}
package com.hospital;

import com.hospital.model.HospitalUser;
import com.hospital.model.Patient;
import com.hospital.repository.HospitalUserRepository;
import com.hospital.repository.PatientRepository;
import com.hospital.repository.UserRoleRepository;
import com.hospital.model.UserRole;
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

    private final List<Patient> patients = new ArrayList<>();
    private final List<HospitalUser> hospitalUsers = new ArrayList<>();

    private final UserRole admin = new UserRole("ROLE_ADMIN");
    private final UserRole doctor = new UserRole("ROLE_DOCTOR");
    private final UserRole receptionist = new UserRole("ROLE_RECEPTIONIST");

    @Override
    public void run(ApplicationArguments args) {
        // insertPatients();
        insertRoles();
        insertHospitalUsers();
        //getValue();
    }

    private void insertRoles() {
         userRoleRepository.save(admin);
         userRoleRepository.save(doctor);
         userRoleRepository.save(receptionist);
    }
    private void insertHospitalUsers() {
        HospitalUser hospitalUser1 = new HospitalUser("Nikita", "Krutoguz", "user0",new BCryptPasswordEncoder(10).encode("123456"));
        hospitalUser1.setRole(userRoleRepository.findOne(1L));
        hospitalUsers.add(hospitalUser1);
        HospitalUser hospitalUser2 = new HospitalUser("Yra", "Knishenko", "user1",new BCryptPasswordEncoder(10).encode("123456"));
        hospitalUser2.setRole(userRoleRepository.findOne(2L));
        hospitalUsers.add(hospitalUser2);
        HospitalUser hospitalUser3 = new HospitalUser("Jeka", "Belousov", "user2",new BCryptPasswordEncoder(10).encode("123456"));
        hospitalUser3.setRole(userRoleRepository.findOne(3L));
        hospitalUsers.add(hospitalUser3);
        hospitalUserRepository.save(hospitalUsers);
    }

    private void insertPatients() {

    }

    private void getValue() {
      HospitalUser hospitalUser = hospitalUserRepository.findUserByLogin("user2");
        System.out.println(hospitalUser.getRole().getRole());
    }
}




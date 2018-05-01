package com.hospital;

import com.hospital.model.HospitalUser;
import com.hospital.model.Patient;
import com.hospital.repository.HospitalUserRepository;
import com.hospital.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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

    private final List<Patient> patients = new ArrayList<>();
    private final List<HospitalUser> hospitalUsers = new ArrayList<>();

    @Override
    public void run(ApplicationArguments args) {
        // insertPatients();
        insertHospitalUsers();
      //  getValue();
    }

    private void insertHospitalUsers(){
        hospitalUsers.add(new HospitalUser("Nikita","Krutoguz","user0"));
        hospitalUsers.add(new HospitalUser("Yra","Knishenko","user1"));
        hospitalUsers.add(new HospitalUser("Jeka","Belousov","user2"));
        hospitalUserRepository.save(hospitalUsers);
    }

    private void insertPatients() {

    }

    /*private void getValue() {
      HospitalUser hospitalUser = hospitalUserRepository.findUserByLogin("user2");
        System.out.println(hospitalUser.getName());
    }*/
}




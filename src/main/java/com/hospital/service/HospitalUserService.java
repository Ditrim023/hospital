package com.hospital.service;

import com.hospital.model.HospitalUser;
import com.hospital.model.Patient;
import com.hospital.repository.HospitalUserRepository;
import com.hospital.repository.UserRoleRepository;
import com.hospital.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nikita Krutoguz
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HospitalUserService {
    private final HospitalUserRepository hospitalUserRepository;
    private final UserRoleRepository userRoleRepository;

    public final List<HospitalUser> getDoctors() {
        final List<HospitalUser> doctors = hospitalUserRepository.findAllUserByRoleId(2L);
        return doctors;
    }

    public final List<Patient> patients(final Long id) {
       // final HospitalUser doctor = hospitalUserRepository.findOne(id);
        List<Patient> patients = hospitalUserRepository.findAllPatientsByTransfer(id);
        return patients;
    }

    public final void createDoctor(final String name, final String surname, final String login, final String password, final String position, final String dateBirth) {
        hospitalUserRepository.save(new HospitalUser(name, surname, login, new BCryptPasswordEncoder(10).encode(password), dateBirth, position, userRoleRepository.findOne(2L)));
    }

    public void hospitalUserUpdate(final Long id, final String name, final String surname, final String position) {
        final HospitalUser doctorFromBase = hospitalUserRepository.findOne(id);
        doctorFromBase.setName(name);
        doctorFromBase.setSurname(surname);
        doctorFromBase.setPosition(position);
        hospitalUserRepository.save(doctorFromBase);
    }

    public final HospitalUser findUserByLogin() {
        return hospitalUserRepository.findUserByLogin(Util.getAuthorizedUserName());
    }

    public List<HospitalUser> findAll() {
        return hospitalUserRepository.findAll();
    }

    public final Boolean isDuplicateUser(String login) {
        final HospitalUser hospitalUser = hospitalUserRepository.findUserByLogin(login);
       if (hospitalUser != null) {
           return false;
        }
        return true;
    }

    public final HospitalUser findOne(final Long hospitalUserId) {
        return hospitalUserRepository.findOne(hospitalUserId);
    }
}

package com.hospital.service;

import com.hospital.model.HospitalUser;
import com.hospital.model.Patient;
import com.hospital.repository.HospitalUserRepository;
import com.hospital.repository.UserRoleRepository;
import com.hospital.repository.UserStatusRepository;
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
    private final UserStatusRepository userStatusRepository;

    public final List<HospitalUser> getDoctors() {
        final List<HospitalUser> doctors = hospitalUserRepository.findAllUserByRoleId(2L);
        return doctors;
    }

    public final List<Patient> patients(final Long id) {
        List<Patient> patients = hospitalUserRepository.findAllPatientsByTransfer(id);
        return patients;
    }

    public final void createDoctor(final String name, final String surname, final String login, final String password, final String position, final String dateBirth, final Long status) {
        hospitalUserRepository.save(new HospitalUser(name, surname, login, new BCryptPasswordEncoder(10).encode(password), dateBirth, position, userRoleRepository.findOne(2L), userStatusRepository.findOne(status)));
    }

    public void hospitalUserUpdate(final Long id, final String name, final String surname, String login, final String position, final Long status) {
        final HospitalUser doctorFromBase = hospitalUserRepository.findOne(id);
        doctorFromBase.setName(name);
        doctorFromBase.setSurname(surname);
        doctorFromBase.setPosition(position);
        doctorFromBase.setLogin(login);
        doctorFromBase.setStatus(userStatusRepository.findOne(status));
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

    public final Boolean isFreeDoc(final Long id) {
        final Long statusId = hospitalUserRepository.getStatusId(id);
        if (statusId == 2) {
            return false;
        }
        return true;
    }

    public final Boolean isFree(final Long id) {
        final Long count = hospitalUserRepository.getCount(id);
        if (count != 0) {
            return false;
        }
        return true;
    }

    public final HospitalUser findOne(final Long hospitalUserId) {
        return hospitalUserRepository.findOne(hospitalUserId);
    }

    public final HospitalUser add(HospitalUser hospitalUser) {
        return hospitalUserRepository.save(hospitalUser);
    }
}

package com.hospital.service;

import com.hospital.model.HospitalUser;
import com.hospital.model.Patient;
import com.hospital.repository.HospitalUserRepository;
import com.hospital.repository.UserRoleRepository;
import com.hospital.repository.UserStatusRepository;
import com.hospital.utils.Util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nikita Krutoguz
 */
@Service
public class HospitalUserService {
    private final HospitalUserRepository hospitalUserRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserStatusRepository userStatusRepository;

    public HospitalUserService(HospitalUserRepository hospitalUserRepository, UserRoleRepository userRoleRepository, UserStatusRepository userStatusRepository) {
        this.hospitalUserRepository = hospitalUserRepository;
        this.userRoleRepository = userRoleRepository;
        this.userStatusRepository = userStatusRepository;
    }

    public final List<HospitalUser> getDoctors() {
        return hospitalUserRepository.findAllUserByRoleId(2L);
    }

    public final List<Patient> patients(final Long id) {
        return hospitalUserRepository.findAllPatientsByTransfer(id);
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

    public final HospitalUser findCurrentUser() {
        return hospitalUserRepository.findUserByLogin(Util.getAuthorizedUserName());
    }

    public List<HospitalUser> findAll() {
        return hospitalUserRepository.findAll();
    }

    public final Boolean isDuplicateUser(String login) {
        final HospitalUser hospitalUser = hospitalUserRepository.findUserByLogin(login);
        return hospitalUser == null;
    }

    public final Boolean isFreeDoc(final Long id) {
        final Long statusId = hospitalUserRepository.getStatusId(id);
        return statusId != 2;
    }

    public final Boolean isFree(final Long id) {
        final Long count = hospitalUserRepository.getCount(id);
        return count == 0;
    }

    public final HospitalUser findOne(final Long hospitalUserId) {
        return hospitalUserRepository.findOne(hospitalUserId);
    }

    public final HospitalUser add(HospitalUser hospitalUser) {
        return hospitalUserRepository.save(hospitalUser);
    }
}

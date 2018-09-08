package com.hospital.repository;

import com.hospital.model.HospitalUser;
import com.hospital.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nikita Krutoguz
 */
@Repository
public interface HospitalUserRepository extends JpaRepository<HospitalUser,Long>{
    @Query(value = "SELECT u FROM HospitalUser u WHERE u.login = :login")
    HospitalUser findUserByLogin(final @Param("login") String login);

    @Query(value = "SELECT u FROM Patient u WHERE u.doctor.id = :id ORDER BY u.dateTransfer DESC")
    List<Patient> findAllPatientsByTransfer(final @Param("id") Long id);

    @Query(value = "SELECT u FROM HospitalUser u WHERE u.roleId = :roleId")
    List<HospitalUser> findAllUserByRoleId(final @Param("roleId") Long roleId);

    @Query(value = "SELECT COUNT (p.id) FROM Patient p WHERE p.doctor.id = :id")
    Long getCount(final @Param("id") Long id);

    @Query(value = "SELECT p.status.id FROM HospitalUser p WHERE p.id = :id")
    Long getStatusId(final @Param("id") Long id);
}

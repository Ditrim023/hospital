package com.hospital.repository;

import com.hospital.model.HospitalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Nikita Krutoguz
 */
@Repository
public interface HospitalUserRepository extends JpaRepository<HospitalUser,Long>{
    @Query(value = "SELECT u FROM HospitalUser u WHERE u.login = :login")
    HospitalUser findUserByLogin(final @Param("login") String login);
}

package com.hospital.repository;

import com.hospital.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nikita Krutoguz
 */
@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus,Long>{
}

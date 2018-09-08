package com.hospital.repository;

import com.hospital.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Nikita Krutoguz
 */
public interface UserStatusRepository extends JpaRepository<UserStatus,Long>{
}

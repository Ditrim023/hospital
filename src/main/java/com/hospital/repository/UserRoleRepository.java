package com.hospital.repository;

import com.hospital.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nikita Krutoguz
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long>{
}

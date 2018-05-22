package com.hospital.repository;

import com.hospital.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nikita Krutoguz
 */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{
}

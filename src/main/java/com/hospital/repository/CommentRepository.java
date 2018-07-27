package com.hospital.repository;

import com.hospital.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Nikita Krutoguz
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment,Long>{
    @Query(value = "SELECT text FROM Comment u WHERE u.id = :id")
    String getTextComment(final @Param("id") Long id);
}

package com.multiauth.multiauthapplication.functions.commentsectionmaster.repository;

import com.multiauth.multiauthapplication.model.CommentSectionMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentSectionMasterRepository extends JpaRepository<CommentSectionMaster, Long> {

    @Query(value = "SELECT * "
            + "FROM CommentSectionMaster "
            + "WHERE productMasterId = :productMasterId ", nativeQuery = true)
    List<CommentSectionMaster> findAllCommentsByProductMasterId(@Param("productMasterId") Long productMasterId);

    @Query(value = "SELECT COUNT(*) "
            + "FROM CommentSectionMaster "
            + "WHERE productMasterId = :productMasterId ", nativeQuery = true)
    Long countAllCommentsByProductMasterId(@Param("productMasterId") Long productMasterId);

}

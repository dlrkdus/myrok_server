package com.example.myrok.repository;

import com.example.myrok.domain.RecordTag;
import com.example.myrok.dto.project.TagDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordTagRepository extends JpaRepository<RecordTag,Long> {
    List<RecordTag> findAllByRecordIdAndDeletedIsFalse(Long id);
    @Query("SELECT rt.tagName FROM RecordTag rt WHERE rt.record.id=:recordId AND rt.deleted=false")
    List<String> findTagsByRecordIdAndDeletedIsFalse(@Param("recordId")Long recordId);

    Long countByProjectIdAndDeletedIsFalse(Long projectId);

    @Query("SELECT new com.example.myrok.dto.project.TagDTO(rt.tagName, COUNT(rt)) " +
            "FROM RecordTag rt " +
            "WHERE rt.projectId = :projectId AND rt.deleted = false " +
            "GROUP BY rt.tagName " +
            "ORDER BY COUNT(rt) DESC")
    Optional<List<TagDTO>> findTagNameAndCountByProjectIdAndDeletedIsFalse(@Param("projectId") Long projectId);
}

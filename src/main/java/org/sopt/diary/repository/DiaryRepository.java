package org.sopt.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {

    @Modifying
    @Query(value = "UPDATE diary_test SET content = :content WHERE id = :id", nativeQuery = true)
    void updateContent(@Param("id") Long id, @Param("content") String content);
}

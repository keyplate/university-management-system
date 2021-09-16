package com.ums.repository;

import com.ums.model.entity.lecturer.Lecturer;
import com.ums.model.entity.subject.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    Subject findSubjectByTitle(String title);

    boolean existsByTitle(String title);

    void deleteSubjectByTitle(String title);

    @Query("select s from Subject s inner join s.courses c inner join c.lecturer l where l = :lecturer")
    Page<Subject> findSubjectsByLecturer(Lecturer lecturer, Pageable pageable);
}

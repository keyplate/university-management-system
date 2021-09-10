package com.ums.repository;

import com.ums.model.grage.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    Set<Grade> findGradesByStudent();
}

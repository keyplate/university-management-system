package com.ums.repository;

import com.ums.model.entity.grage.Grade;
import com.ums.model.entity.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    Set<Grade> findGradesByStudent(Student student);
}

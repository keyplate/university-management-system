package com.ums.repository;

import com.ums.model.entity.course.Course;
import com.ums.model.entity.grade.Grade;
import com.ums.model.entity.student.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GradeRepository extends JpaRepository<Grade, Long> {

    Page<Grade> findGradesByStudent(Student student, Pageable pageable);

    Grade findGradeByStudentAndAndCourse(Student student, Course course);
}

package com.ums.repository;

import com.ums.model.entity.course.Course;
import com.ums.model.entity.lecturer.Lecturer;
import com.ums.model.entity.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Set<Course> findCoursesByLecturer(Lecturer lecturer);

    Set<Course> findCoursesBySubject(Subject subject);
}

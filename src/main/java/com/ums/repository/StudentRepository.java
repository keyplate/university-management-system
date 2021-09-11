package com.ums.repository;

import com.ums.model.entity.group.StudentGroup;
import com.ums.model.entity.student.Student;
import java.util.Set;

public interface StudentRepository extends AccountRepository<Student> {

    Set<Student> findStudentsByGroup(StudentGroup group);
}

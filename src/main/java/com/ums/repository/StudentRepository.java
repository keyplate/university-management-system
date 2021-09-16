package com.ums.repository;

import com.ums.model.entity.group.StudentGroup;
import com.ums.model.entity.student.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface StudentRepository extends AbstractAccountRepository<Student> {

    Page<Student> findStudentsByGroup(StudentGroup group, Pageable pageable);
}

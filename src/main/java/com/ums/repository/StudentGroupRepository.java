package com.ums.repository;

import com.ums.model.entity.group.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentGroupRepository extends JpaRepository<StudentGroup, Integer> {

    StudentGroup findStudentGroupByName(String name);
}

package com.ums.repository;

import com.ums.model.entity.group.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentGroupRepository extends JpaRepository<StudentGroup, Integer> {

    boolean existsByName(String name);

    StudentGroup findStudentGroupByName(String name);

    void deleteStudentGroupByName(String name);
}

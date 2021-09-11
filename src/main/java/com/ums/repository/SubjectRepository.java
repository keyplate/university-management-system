package com.ums.repository;

import com.ums.model.entity.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Set;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    Set<Subject> findSubjectsByTitle(String title);
}

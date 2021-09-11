package com.ums.service;

import com.ums.model.auth.StudentDetails;
import com.ums.model.entity.account.AccountStatus;
import com.ums.model.entity.student.SaveStudentRequest;
import com.ums.model.entity.student.Student;
import com.ums.model.entity.student.StudentResponse;
import com.ums.repository.StudentGroupRepository;
import com.ums.repository.StudentRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService implements UserDetailsService {
    private StudentRepository students;
    private StudentGroupRepository groupRepository;
    public StudentService(StudentRepository students, StudentGroupRepository groupRepository) {
        this.students = students;
        this.groupRepository = groupRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = students.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("No student account related to this email " + email)
        );
        return new StudentDetails(student);
    }

    @Transactional
    public StudentResponse create(SaveStudentRequest studentRequest){
        Student student = new Student();
        student.setEmail(studentRequest.getEmail());
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setGroup(groupRepository.findStudentGroupByName(studentRequest.getGroup()));
        student.setStatus(AccountStatus.ACTIVE);
        return StudentResponse.studentResponse(student);
    }
}

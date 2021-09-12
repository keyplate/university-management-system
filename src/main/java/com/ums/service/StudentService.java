package com.ums.service;

import com.ums.exceptions.DuplicateEntityException;
import com.ums.model.auth.StudentDetails;
import com.ums.model.entity.account.AccountStatus;
import com.ums.model.request.SaveStudentRequest;
import com.ums.model.entity.student.Student;
import com.ums.model.response.SaveStudentResponse;
import com.ums.repository.StudentGroupRepository;
import com.ums.repository.StudentRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService implements UserDetailsService {
    private StudentRepository studentRepository;
    private StudentGroupRepository groupRepository;
    public StudentService(StudentRepository studentRepository, StudentGroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("No student account related to this email " + email)
        );
        return new StudentDetails(student);
    }

    @Transactional
    public SaveStudentResponse create(SaveStudentRequest studentRequest){
        validate(studentRequest);
        Student student = new Student();
        student.setEmail(studentRequest.getEmail());
        student.setPassword(studentRequest.getPassword()); //Don't forget to add encoding!!!
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setGroup(groupRepository.findStudentGroupByName(studentRequest.getGroup()));
        student.setStatus(AccountStatus.ACTIVE);
        studentRepository.save(student);
        return SaveStudentResponse.studentResponse(student);
    }

    private void validate(SaveStudentRequest studentRequest) {
        if(studentRepository.existsByEmail(studentRequest.getEmail())) {
            throw new DuplicateEntityException("Student with this email already exists " + studentRequest.getEmail());
        }
    }
}

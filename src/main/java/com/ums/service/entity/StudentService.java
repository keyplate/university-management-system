package com.ums.service.entity;

import com.ums.exceptions.DuplicateEntityException;
import com.ums.model.auth.Roles;
import com.ums.model.entity.account.AccountStatus;
import com.ums.model.request.SaveStudentRequest;
import com.ums.model.entity.student.Student;
import com.ums.model.request.StudentGroupRequest;
import com.ums.model.response.StudentGroupResponse;
import com.ums.model.response.StudentResponse;
import com.ums.repository.StudentGroupRepository;
import com.ums.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService{

    private StudentRepository studentRepository;
    private StudentGroupRepository groupRepository;
    private PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository studentRepository, StudentGroupRepository groupRepository,
                          PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public StudentResponse create(SaveStudentRequest studentRequest){
        validate(studentRequest);
        Student student = new Student();
        student.setEmail(studentRequest.getEmail());
        student.setPassword(passwordEncoder.encode(studentRequest.getPassword())); //Don't forget to add encoding!!!
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setGroup(groupRepository.findStudentGroupByName(studentRequest.getGroup()));
        student.setStatus(AccountStatus.ACTIVE);
        student.setRole(Roles.ROLE_STUDENT.name());
        studentRepository.save(student);
        return StudentResponse.studentResponse(student);
    }

    @Transactional(readOnly = true)
    public Page<StudentResponse> getStudentsInGroup(StudentGroupRequest group, Pageable pageable) {
        return studentRepository
                .findStudentsByGroup(groupRepository.findStudentGroupByName(group.getName()), pageable)
                .map(StudentResponse::studentResponse);
    }

    @Transactional(readOnly = true)
    public Page<StudentResponse> getClassmates(int id, Pageable pageable) {
        return studentRepository.findStudentsByGroup(studentRepository.getById(id).getGroup(), pageable)
                .map(StudentResponse::studentResponse);
    }


    private void validate(SaveStudentRequest studentRequest) {
        if(studentRepository.existsByEmail(studentRequest.getEmail())) {
            throw new DuplicateEntityException("Student with this email already exists " + studentRequest.getEmail());
        }
    }
}

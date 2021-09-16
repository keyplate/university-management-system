package com.ums.service.entity;

import com.ums.exceptions.DuplicateEntityException;
import com.ums.model.auth.Roles;
import com.ums.model.entity.lecturer.Lecturer;
import com.ums.model.request.SaveLecturerRequest;
import com.ums.model.response.LecturerResponse;
import com.ums.model.response.SubjectResponse;
import com.ums.repository.LecturerRepository;
import com.ums.repository.SubjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;

@Service
public class LecturerService {

    private LecturerRepository lecturerRepository;
    private PasswordEncoder passwordEncoder;
    private SubjectRepository subjectRepository;

    public LecturerService(LecturerRepository lecturerRepository, PasswordEncoder passwordEncoder, SubjectRepository subjectRepository) {
        this.lecturerRepository = lecturerRepository;
        this.passwordEncoder = passwordEncoder;
        this.subjectRepository = subjectRepository;
    }

    @Transactional
    public LecturerResponse createLecturer(SaveLecturerRequest lecturerRequest) {
        validate(lecturerRequest);
        Lecturer lecturer = new Lecturer();
        lecturer.setEmail(lecturerRequest.getEmail());
        lecturer.setFirstName(lecturerRequest.getFirstName());
        lecturer.setLastName(lecturerRequest.getLastName());
        lecturer.setPassword(passwordEncoder.encode(lecturerRequest.getPassword()));
        lecturer.setRole(Roles.ROLE_LECTURER.name());
        lecturerRepository.save(lecturer);
        return LecturerResponse.lecturerResponse(lecturer);
    }

    @Transactional(readOnly = true)
    public Page<SubjectResponse> getSubjects(int id, Pageable pageable) {
        return subjectRepository
                .findSubjectsByLecturer(lecturerRepository.getById(id), pageable)
                .map(SubjectResponse::subjectResponse);
    }

    @Transactional(readOnly = true)
    public Page<LecturerResponse> getLecturerList(Pageable pageable) {
        return lecturerRepository.findAll(pageable)
                .map(LecturerResponse::lecturerResponse);
    }

    @Transactional
    public void deleteLecturerById(int id) {
        if (!lecturerRepository.existsById(id)) {
            throw new EntityNotFoundException("Lecturer with this id doesn't exist " + id);
        }
        lecturerRepository.deleteById(id);
    }

    @Transactional
    public void deleteLecturerByEmail(String email) {
        if (!lecturerRepository.existsByEmail(email)) {
            throw new EntityNotFoundException("Lecturer with this email doesn't exist " + email);
        }
        lecturerRepository.deleteAccountByEmail(email);
    }

    private void validate(SaveLecturerRequest lecturerRequest) {
        if (lecturerRepository.existsByEmail(lecturerRequest.getEmail())) {
            throw new DuplicateEntityException("Lecturer with this email already exists " + lecturerRequest.getEmail());
        }
    }
}

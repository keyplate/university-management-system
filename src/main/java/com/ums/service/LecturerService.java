package com.ums.service;

import com.ums.exceptions.DuplicateEntityException;
import com.ums.model.entity.lecturer.Lecturer;
import com.ums.model.request.SaveLecturerRequest;
import com.ums.model.response.SaveLecturerResponse;
import com.ums.repository.LecturerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class LecturerService {

    private LecturerRepository lecturerRepository;

    public LecturerService(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Transactional
    public SaveLecturerResponse createLecturer(SaveLecturerRequest lecturerRequest) {
        validate(lecturerRequest);
        Lecturer lecturer = new Lecturer();
        lecturer.setEmail(lecturerRequest.getEmail());
        lecturer.setFirstName(lecturerRequest.getFirstName());
        lecturer.setLastName(lecturerRequest.getLastName());
        lecturer.setPassword(lecturerRequest.getPassword());
        lecturerRepository.save(lecturer);
        return SaveLecturerResponse.lecturerResponse(lecturer);
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

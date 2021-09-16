package com.ums.service.entity;

import com.ums.exceptions.DuplicateEntityException;
import com.ums.model.entity.subject.Subject;
import com.ums.model.request.SubjectRequest;
import com.ums.model.response.SubjectResponse;
import com.ums.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class SubjectService {
    private SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Transactional
    public SubjectResponse create(SubjectRequest subjectRequest) {
        validateSubject(subjectRequest.getTitle());
        var subject = new Subject();
        subject.setTitle(subjectRequest.getTitle());
        subjectRepository.save(subject);
        return SubjectResponse.subjectResponse(subject);
    }

    @Transactional
    public SubjectResponse update(String title, SubjectRequest subjectRequest) {
        Subject subject = subjectRepository.findSubjectByTitle(title);
        subject.setTitle(subjectRequest.getTitle());
        return SubjectResponse.subjectResponse(subject);
    }

    @Transactional
    public void delete(String name) {
        if (!subjectRepository.existsByTitle(name)) {
            throw new EntityNotFoundException(name);
        }
        subjectRepository.deleteSubjectByTitle(name);
    }

    private void validateSubject(String subjectName) {
        if (subjectRepository.existsByTitle(subjectName)) {
            throw new DuplicateEntityException(subjectName);
        }
    }
}

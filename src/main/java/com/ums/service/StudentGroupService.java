package com.ums.service;

import com.ums.exceptions.DuplicateEntityException;
import com.ums.model.entity.group.StudentGroup;
import com.ums.model.request.StudentGroupRequest;
import com.ums.model.response.StudentGroupResponse;
import com.ums.repository.StudentGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class StudentGroupService {
    private StudentGroupRepository groupRepository;

    public StudentGroupService(StudentGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Transactional
    public StudentGroupResponse create(StudentGroupRequest groupRequest) {
        validateGroup(groupRequest.getName());
        var group = new StudentGroup();
        group.setName(groupRequest.getName());
        groupRepository.save(group);
        return StudentGroupResponse.groupResponse(group);
    }

    @Transactional
    public StudentGroupResponse update(String name, StudentGroupRequest groupRequest) {
        StudentGroup group = groupRepository.findStudentGroupByName(name);
        group.setName(groupRequest.getName());
        return StudentGroupResponse.groupResponse(group);
    }

    @Transactional
    public void delete(String name) {
        if (!groupRepository.existsByName(name)) {
            throw new EntityNotFoundException(name);
        }
        groupRepository.deleteStudentGroupByName(name);
    }

    private void validateGroup(String groupName) {
        if (groupRepository.existsByName(groupName)) {
            throw new DuplicateEntityException(groupName);
        }
    }
}

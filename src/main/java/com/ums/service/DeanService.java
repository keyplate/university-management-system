package com.ums.service;

import com.ums.exceptions.DuplicateEntityException;
import com.ums.model.entity.account.AccountStatus;

import com.ums.model.entity.dean.Dean;
import com.ums.model.request.SaveDeanRequest;
import com.ums.model.request.SaveLecturerRequest;
import com.ums.model.response.SaveDeanResponse;
import com.ums.repository.DeanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Service
public class DeanService {
    private DeanRepository deanRepository;

    public DeanService(DeanRepository deanRepository) {
        this.deanRepository = deanRepository;
    }

    @Transactional
    public SaveDeanResponse create(SaveDeanRequest deanRequest){
        validate(deanRequest);
        Dean dean = new Dean();
        dean.setEmail(deanRequest.getEmail());
        dean.setPassword(deanRequest.getPassword()); //Don't forget to add encoding!!!
        dean.setFirstName(deanRequest.getFirstName());
        dean.setLastName(deanRequest.getLastName());
        dean.setStatus(AccountStatus.ACTIVE);
        deanRepository.save(dean);
        return SaveDeanResponse.deanResponse(dean);
    }


    @Transactional
    public void deleteDean(String email) {
        if (!deanRepository.existsByEmail(email)) {
            throw new EntityNotFoundException("Dean with this email doesn't exist " + email);
        }
        deanRepository.deleteAccountByEmail(email);
    }

    private void validate(SaveDeanRequest deanRequest) {
        if(deanRepository.existsByEmail(deanRequest.getEmail())) {
            throw new DuplicateEntityException("Dean with this email already exists " + deanRequest.getEmail());
        }
    }

}

package com.ums.service;

import com.ums.exceptions.DuplicateEntityException;
import com.ums.model.entity.account.AccountStatus;

import com.ums.model.entity.dean.Dean;
import com.ums.model.request.SaveDeanRequest;
import com.ums.model.response.SaveDeanResponse;
import com.ums.repository.DeanRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Service
public class DeanService {
    private DeanRepository deanRepository;
    private PasswordEncoder passwordEncoder;

    public DeanService(DeanRepository deanRepository, PasswordEncoder passwordEncoder) {
        this.deanRepository = deanRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public SaveDeanResponse create(SaveDeanRequest deanRequest){
        validate(deanRequest);
        Dean dean = new Dean();
        dean.setEmail(deanRequest.getEmail());
        dean.setPassword(passwordEncoder.encode(deanRequest.getPassword())); //Don't forget to add encoding!!!
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

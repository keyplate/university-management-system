package com.ums.service.entity;

import com.ums.exceptions.DuplicateEntityException;
import com.ums.model.auth.Roles;
import com.ums.model.entity.account.AccountStatus;

import com.ums.model.entity.dean.Dean;
import com.ums.model.request.SaveDeanRequest;
import com.ums.model.response.DeanResponse;
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
    public DeanResponse create(SaveDeanRequest deanRequest){
        validate(deanRequest);
        Dean dean = new Dean();
        dean.setEmail(deanRequest.getEmail());
        dean.setPassword(passwordEncoder.encode(deanRequest.getPassword()));
        dean.setFirstName(deanRequest.getFirstName());
        dean.setLastName(deanRequest.getLastName());
        dean.setStatus(AccountStatus.ACTIVE);
        dean.setRole(Roles.ROLE_DEAN.name());
        deanRepository.save(dean);
        return DeanResponse.deanResponse(dean);
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

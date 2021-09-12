package com.ums.model.response;

import com.ums.model.entity.account.Account;
import com.ums.model.entity.account.AccountStatus;
import lombok.Data;

@Data
public class AccountResponse {

    protected Integer id;
    protected String email;
    protected String firstName;
    protected String lastName;
    protected AccountStatus status;

    public static AccountResponse accountResponse(Account account) {
        AccountResponse responseObject = new AccountResponse();
        responseObject.setId(account.getId());
        responseObject.setEmail(account.getEmail());
        responseObject.setFirstName(account.getFirstName());
        responseObject.setLastName(account.getLastName());
        responseObject.setStatus(account.getStatus());
        return responseObject;
    }
}
